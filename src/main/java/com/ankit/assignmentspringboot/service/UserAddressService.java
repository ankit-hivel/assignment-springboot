package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.UserAddressModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserAddressRepository;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.SaveUserAddressRequestDto;
import com.ankit.assignmentspringboot.requestDto.UpdateUserAddressRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserAddressResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserAddressService(UserAddressRepository userAddressRepository, UserRepository userRepository, UserService userService) {
        this.userAddressRepository = userAddressRepository;
        this.userRepository = userRepository;
    }

    public UserAddressModel saveUserAddress(SaveUserAddressRequestDto userAddressPayload) {
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
        System.out.println("existing user found");
        userAddressToSave.setUser(userToRefer);

        return userAddressRepository.save(userAddressToSave);
    }

    public GetUserAddressResponseDto getUserAddressById(int id){
        UserAddressModel userAddress = userAddressRepository.findById(id).orElseThrow(
                () -> new RuntimeException("no associated address found")
        );

        GetUserAddressResponseDto userAddressDto = new GetUserAddressResponseDto(userAddress);
        return userAddressDto;
    }

    @Transactional
    public void updateUserAddressData(UpdateUserAddressRequestDto dto) {

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
    }

    public void deleteUserAddress(int id){
        userAddressRepository.deleteById(id);
    }
}
