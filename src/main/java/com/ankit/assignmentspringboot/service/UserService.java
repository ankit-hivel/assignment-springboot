package com.ankit.assignmentspringboot.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserResponseDto;
import com.ankit.assignmentspringboot.utility.CONSTANTS;
import com.ankit.assignmentspringboot.utility.GetAuthUserId;
import com.ankit.assignmentspringboot.utility.RedisClient;
import com.ankit.assignmentspringboot.utility.UserRole;
import com.ankit.assignmentspringboot.utility.security.GetAuthUserRole;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final GetAuthUserRole getAuthUserRole;

    @Autowired
    public UserService(UserRepository userRepository, GetAuthUserRole getAuthUserRole){
        this.userRepository = userRepository;
        this.getAuthUserRole = getAuthUserRole;
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
        Jedis redis = RedisClient.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        if (redis != null){
            String user = redis.get(id);
            log.info("user from redis{}", user);
            if (user != null) {
                log.info("cache hit");
                return objectMapper.readValue(user, UserModel.class);
            }
        }
        log.info("cache miss");
        UserModel user1 = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        if (redis != null) {
            String userJsonToStore = objectMapper.writeValueAsString(new GetUserResponseDto(user1));
            redis.set(id, userJsonToStore, new SetParams().ex(CONSTANTS.RedisValueExpiry));
        }
        return user1;
    }

    public UserModel getUserByEmail(String email){
        ObjectMapper objectMapper = new ObjectMapper();
        Jedis redis = RedisClient.getInstance();
        if (redis != null) {
            String user = redis.get(email);
            if (user != null) {
                log.info("cache hit");
                return objectMapper.readValue(user, UserModel.class);
            }
        }
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
        log.info("existing user: {}", existingUser);
        if (redis != null) {
            String userJsonToStore = objectMapper.writeValueAsString(new GetUserResponseDto(existingUser));
            redis.set(email, userJsonToStore, new SetParams().ex(CONSTANTS.RedisValueExpiry));
        }
        return existingUser;
    }

    public List<UserModel> getAllUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        Jedis redis = RedisClient.getInstance();
        if (redis != null) {
            String allUsers = redis.get(CONSTANTS.GetAllUsersKey);
            if(allUsers != null) {
                log.info("cache hit");
                return objectMapper.readValue(allUsers, new TypeReference<List<UserModel>>() {
                });
            }
        }
        log.info("cache miss");
        List<UserModel> allUsers = userRepository.findAll();
        List<GetUserResponseDto> users = allUsers.stream().map(GetUserResponseDto::new).toList();
        String allUsersJsonToWrite = objectMapper.writeValueAsString(users);
        if (redis != null) {
            redis.set(CONSTANTS.GetAllUsersKey, allUsersJsonToWrite, new SetParams().ex(CONSTANTS.RedisValueExpiry));
        }
        return allUsers;
    }

    public void updateExistingUserData(SaveUserRequestDto user) {
        if (
                !Objects.equals(user.getId(), GetAuthUserId.getUserId())
                || (!(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole())))
        ) {
            throw new RuntimeException("unauthorized user");
        }

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

        // invalidate cache
        Jedis redis = RedisClient.getInstance();
        if (redis != null) {
            redis.del(String.valueOf(userToUpdate.getId()));
            redis.del(userToUpdate.getEmail());
        }
    }

    @Transactional
    public void deleteUser(String id) {
        UserModel user = userRepository.findById(id).orElseThrow();

        // invalidate cache
        Jedis redis = RedisClient.getInstance();
        if (redis != null) {
            redis.del(String.valueOf(user.getId()));
            redis.del(user.getEmail());
        }
        userRepository.deleteById(id);
    }
}
