package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.UserAddressModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserAddressRepository;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.SaveUserAddressRequestDto;
import com.ankit.assignmentspringboot.requestDto.UpdateUserAddressRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserAddressResponseDto;
import com.ankit.assignmentspringboot.utility.CONSTANTS;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class UserAddressService {
    private static final Logger log = LoggerFactory.getLogger(UserAddressService.class);
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;
    private final RedisService redis;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserAddressService(UserAddressRepository userAddressRepository, UserRepository userRepository, RedisService redisService, ObjectMapper objectMapper) {
        this.userAddressRepository = userAddressRepository;
        this.userRepository = userRepository;
        this.redis = redisService;
        this.objectMapper = objectMapper;
    }

    public void saveUserAddress(SaveUserAddressRequestDto userAddressPayload) {
        UserAddressModel userAddressToSave = new UserAddressModel();
        userAddressToSave.setArea(userAddressPayload.getArea());
        userAddressToSave.setCity(userAddressPayload.getCity());
        userAddressToSave.setState(userAddressPayload.getState());
        userAddressToSave.setStateCode(userAddressPayload.getStateCode());
        userAddressToSave.setCountry(userAddressPayload.getCountry());
        userAddressToSave.setPostalCode(userAddressPayload.getPostalCode());
        userAddressToSave.setLatitude(userAddressPayload.getLatitude());
        userAddressToSave.setLongitude(userAddressPayload.getLongitude());
        UserModel userToRefer = userRepository.findById(userAddressPayload.getUser_id()).orElseThrow(
                ()-> new RuntimeException("user not found")
        );
        log.info("existing user found");
        userAddressToSave.setUser(userToRefer);

        userAddressRepository.save(userAddressToSave);
    }

    public GetUserAddressResponseDto getUserAddressById(int id){
        String redisKey = "ua:" + id;
        String redisValue = redis.get(redisKey);
        if (redisValue != null) {
            log.info("cache hit");
            return objectMapper.readValue(redisValue, GetUserAddressResponseDto.class);
        }
        log.info("cache miss");

        UserAddressModel userAddress = userAddressRepository.findById(id).orElseThrow(
                () -> new RuntimeException("no associated address found")
        );

        String jsonToStore = objectMapper.writeValueAsString(new GetUserAddressResponseDto(userAddress));
        redis.saveWithTTL(redisKey, jsonToStore, 5, TimeUnit.MINUTES);

        return new GetUserAddressResponseDto(userAddress);
    }

    @Transactional
    public void updateUserAddressData(UpdateUserAddressRequestDto dto) {
        String redisKey = CONSTANTS.getUserAddressRedisKey(dto.getId());

        UserAddressModel userAddress = userAddressRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (dto.getArea() != null)
            userAddress.setArea(dto.getArea());

        if (dto.getCity() != null)
            userAddress.setCity(dto.getCity());

        if (dto.getState() != null)
            userAddress.setState(dto.getState());

        if (dto.getStateCode() != null)
            userAddress.setStateCode(dto.getStateCode());

        if (dto.getCountry() != null)
            userAddress.setCountry(dto.getCountry());

        if (dto.getPostalCode() != null)
            userAddress.setPostalCode(dto.getPostalCode());

        if (dto.getLatitude() != null)
            userAddress.setLatitude(dto.getLatitude());

        if (dto.getLongitude() != null)
            userAddress.setLongitude(dto.getLongitude());

        userAddressRepository.save(userAddress);
        String jsonToWrite = objectMapper.writeValueAsString(new GetUserAddressResponseDto(userAddress));
        redis.saveWithTTL(redisKey, jsonToWrite, 5, TimeUnit.MINUTES);
    }

    public void deleteUserAddress(Integer id){
        userAddressRepository.deleteById(id);
        redis.del(CONSTANTS.getUserAddressRedisKey(id));
    }
}
