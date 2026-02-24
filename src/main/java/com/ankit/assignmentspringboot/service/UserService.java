package com.ankit.assignmentspringboot.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyService companyService;

    @Autowired
    public UserService(UserRepository userRepository, CompanyService companyService){
        this.userRepository = userRepository;
        this.companyService = companyService;
    }

    public void saveUserData(SaveUserRequestDto userPayload){
        UserModel user = new UserModel(userPayload);

        // hash password
        String hashedPassword = BCrypt.withDefaults().hashToString(12, userPayload.getPassword().toCharArray());
        user.setPassword(hashedPassword);

        // save user
        userRepository.save(user);
    }

    public UserModel getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
    }

    public UserModel getUserByEmail(String email){
        UserModel user = new UserModel();
        user.setEmail(email);
        UserModel existingUser = userRepository.findOne(
                Example.of(user, ExampleMatcher.matchingAny()
                        .withMatcher(
                                "email",
                                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()
                        )
                )
        ).orElseThrow(()-> new RuntimeException("user not found"));
        System.out.println("existing user: " + existingUser);
        return existingUser;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateExistingUserData(SaveUserRequestDto user) {

        UserModel userToUpdate = getUserByEmail(user.getEmail());

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

        userRepository.save(userToUpdate);
    }

    @Transactional
    public void deleteUser(Integer id) {
        UserModel user = userRepository.findById(id).orElseThrow();
        System.out.println(user);
        System.out.println(user.getCompany());
        System.out.println(user.getUserAddress());
//        companyService.deleteCompanyById(user.getCompany().getId());
//        userAddressService.deleteUserAddress(user.getUserAddress().getId());
        userRepository.deleteById(id);
    }
}
