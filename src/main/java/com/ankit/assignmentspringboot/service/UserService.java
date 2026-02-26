package com.ankit.assignmentspringboot.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserResponseDto;
import com.ankit.assignmentspringboot.utility.CONSTANTS;
import com.ankit.assignmentspringboot.utility.GetAuthUserId;
import com.ankit.assignmentspringboot.utility.UserRole;
import com.ankit.assignmentspringboot.utility.security.GetAuthUserRole;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final GetAuthUserRole getAuthUserRole;

    private final RedisService redis;

    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, GetAuthUserRole getAuthUserRole, RedisService redis, ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.getAuthUserRole = getAuthUserRole;
        this.redis = redis;
        this.objectMapper = objectMapper;
    }

    public UserModel saveUserData(SaveUserRequestDto userPayload){
        UserModel user = new UserModel(userPayload);
        // hash password
        String hashedPassword = BCrypt.withDefaults().hashToString(12, userPayload.getPassword().toCharArray());
        user.setPassword(hashedPassword);

        // save user
        return userRepository.save(user);
    }

    public UserModel getUserById(String id) {
        String redisKey = CONSTANTS.getUserRedisKey(id);
        String redisValue = redis.get(redisKey);
        log.info("user from redis: {}", redisValue);
        if (redisValue != null) {
            log.info("cache hit");
            return objectMapper.readValue(redisValue, UserModel.class);
        }
        log.info("cache miss");
        UserModel user1 = userRepository.findByIdAndIsDeleted(id, false).orElseThrow(()-> new RuntimeException("user not found"));
        String userJsonToStore = objectMapper.writeValueAsString(new GetUserResponseDto(user1));
        redis.saveWithTTL(redisKey, userJsonToStore,5, TimeUnit.MINUTES);
        return user1;
    }

    public UserModel getUserByEmail(String email){
        String redisKey = CONSTANTS.getUserRedisKey(email);
        String redisValue = redis.get(redisKey);
        if (redisValue != null) {
            log.info("cache hit");
            return objectMapper.readValue(redisValue, UserModel.class);
        }
        UserModel existingUser = userRepository.findByEmailIgnoreCaseAndIsDeleted(email, false)
                .orElseThrow(()-> new RuntimeException("user not found"));
        log.info("existing user: {}", existingUser);
        String userJsonToStore = objectMapper.writeValueAsString(new GetUserResponseDto(existingUser));
        redis.saveWithTTL(redisKey, userJsonToStore, 5, TimeUnit.MINUTES);
        return existingUser;
    }

    public List<GetUserResponseDto> getAllUsers(
            Integer page, Integer count, String sortby, Boolean ascending
    ) {
        log.info("=================================== -> {}" ,ascending.toString());
        Sort sortingParams = ascending ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
        Pageable pageable = PageRequest.of(page, count, sortingParams);
        Page<UserModel> allUsers = userRepository.findAllByIsDeleted(false, pageable);
        List<GetUserResponseDto> users = allUsers.stream().map(GetUserResponseDto::new).toList();
        return users;
    }

    @Transactional
    public void updateExistingUserData(SaveUserRequestDto user) {
        log.info("updating user...");
        if (
                !(Objects.equals(user.getId(), GetAuthUserId.getUserId()))
                && (!(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole())))
        ) {
            log.info(user.getId());
            log.info(GetAuthUserId.getUserId());
            log.info(getAuthUserRole.getUserRole().toString());
            throw new RuntimeException("unauthorized user");
        }

        UserModel userToUpdate = userRepository.findByIdAndIsDeleted(user.getId(), false).orElseThrow(
                () -> new RuntimeException("user not found")
        );

        if (user.getFirstName() != null)
            userToUpdate.setFirstName(user.getFirstName());

        if (user.getLastName() != null)
            userToUpdate.setLastName(user.getLastName());

        if (user.getMaidenName() != null)
            userToUpdate.setMaidenName(user.getMaidenName());

        if (user.getAge() != null)
            userToUpdate.setAge(user.getAge());

        if (user.getGender() != null)
            userToUpdate.setGender(user.getGender());

        if (user.getEmail() != null)
            userToUpdate.setEmail(user.getEmail());

        if (user.getPhone() != null)
            userToUpdate.setPhone(user.getPhone());

        if (user.getUsername() != null)
            userToUpdate.setUsername(user.getUsername());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            userToUpdate.setPassword(
                    BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray())
            );
        }

        if (user.getBirthDate() != null)
            userToUpdate.setBirthDate(user.getBirthDate());

        if (user.getImage() != null)
            userToUpdate.setImage(user.getImage());

        if (user.getBloodGroup() != null)
            userToUpdate.setBloodGroup(user.getBloodGroup());

        if (user.getHeight() != null)
            userToUpdate.setHeight(user.getHeight());

        if (user.getWeight() != null)
            userToUpdate.setWeight(user.getWeight());

        if (user.getEyeColor() != null)
            userToUpdate.setEyeColor(user.getEyeColor());

        if (user.getHaircolor() != null)
            userToUpdate.setHaircolor(user.getHaircolor());

        if (user.getHairtype() != null)
            userToUpdate.setHairtype(user.getHairtype());

        if (user.getIp() != null)
            userToUpdate.setIp(user.getIp());

        if (user.getMacAddress() != null)
            userToUpdate.setMacAddress(user.getMacAddress());

        if (user.getUniversity() != null)
            userToUpdate.setUniversity(user.getUniversity());

        if (user.getEin() != null)
            userToUpdate.setEin(user.getEin());

        if (user.getSsn() != null)
            userToUpdate.setSsn(user.getSsn());

        if (user.getUserAgent() != null)
            userToUpdate.setUserAgent(user.getUserAgent());

        if (user.getRole() != null)
            userToUpdate.setRole(user.getRole());

        // invalidate cache
        redis.del(CONSTANTS.getUserRedisKey(userToUpdate.getId()));
        redis.del(CONSTANTS.getUserRedisKey(userToUpdate.getEmail()));
    }

    @Transactional
    public void deleteUser(String id) {
        UserModel user = userRepository.findById(id).orElseThrow();

        // invalidate cache
        redis.del(CONSTANTS.getUserRedisKey(id));
        redis.del(CONSTANTS.getUserRedisKey(user.getEmail()));
        userRepository.deleteById(id);
    }

    @Transactional
    public void softDeleteUserByUserId(String id) {
        UserModel user = userRepository.findById(id).orElseThrow();

        // invalidate cache
        redis.del(CONSTANTS.getUserRedisKey(id));
        redis.del(CONSTANTS.getUserRedisKey(user.getEmail()));
        user.setDeleted(true);
    }

    @Transactional
    public void restoreSoftDeletedUserByUserId(String id) {
        UserModel user = userRepository.findById(id).orElseThrow();
        user.setDeleted(false);
    }
}
