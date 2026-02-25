package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.requestDto.CompanyAddressDto;
import com.ankit.assignmentspringboot.requestDto.SaveCompanyRequestDto;
import com.ankit.assignmentspringboot.requestDto.SaveUserAddressRequestDto;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetAllUsersDummyApiResponse;
import com.ankit.assignmentspringboot.responseDto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestApiService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private CompanyService companyService;


    private void saveToDb (List<User> users) {
        try {
            for (User user : users) {
                // create SaveUserRequestDto object
                SaveUserRequestDto userToSave = new SaveUserRequestDto();
                userToSave.setId(user.getId());
                userToSave.setAge(user.getAge());
                userToSave.setEin(user.getEin());
                userToSave.setEmail(user.getEmail());
                userToSave.setGender(user.getGender());
                userToSave.setBirthDate(user.getBirthDate());
                userToSave.setBloodGroup(user.getBloodGroup());
                userToSave.setEyeColor(user.getEyeColor());
                userToSave.setFirstName(user.getFirstName());
                userToSave.setLastName(user.getLastName());
                userToSave.setHaircolor(user.getHaircolor());
                userToSave.setHairtype(user.getHairtype());
                userToSave.setHeight(user.getHeight());
                userToSave.setId(user.getId());
                userToSave.setImage(user.getImage());
                userToSave.setIp(user.getIp());
                userToSave.setMacAddress(user.getMacAddress());
                userToSave.setMaidenName(user.getMaidenName());
                userToSave.setPassword(user.getBloodGroup());
                userToSave.setPhone(user.getPhone());
                userToSave.setRole(user.getRole());
                userToSave.setSsn(user.getSsn());
                userToSave.setUniversity(user.getUniversity());
                userToSave.setUserAgent(user.getUserAgent());
                userToSave.setUsername(user.getUsername());
                userToSave.setWeight(user.getWeight());

                // save user
                UserModel savedUser = this.userService.saveUserData(userToSave);

                // user address
                SaveUserAddressRequestDto userAddress = new SaveUserAddressRequestDto();
                User.UserAddressApiResponse argUserAddress = user.getAddress();
                userAddress.setArea(argUserAddress.getArea());
                userAddress.setStateCode(argUserAddress.getStateCode());
                userAddress.setState(argUserAddress.getState());
                userAddress.setCity(argUserAddress.getCity());
                userAddress.setCountry(argUserAddress.getCountry());
                userAddress.setLongitude(argUserAddress.getCoordinates().getLongitude());
                userAddress.setLatitude(argUserAddress.getCoordinates().getLatitude());
                userAddress.setPostalCode(argUserAddress.getPostalCode());
                userAddress.setUser_id(savedUser.getId());
                // save address
                userAddressService.saveUserAddress(userAddress);

                // company with address

                CompanyAddressDto cAddressToSave = new CompanyAddressDto();
                User.UserAddressApiResponse cAddr = user.getCompany().getAddress();
                cAddressToSave.setArea(cAddr.getArea());
                cAddressToSave.setCity(cAddr.getCity());
                cAddressToSave.setLat(cAddr.getCoordinates().getLatitude());
                cAddressToSave.setLng(cAddr.getCoordinates().getLongitude());
                cAddressToSave.setState(cAddr.getState());
                cAddressToSave.setPostalCode(cAddr.getPostalCode());
                cAddressToSave.setStateCode(cAddr.getStateCode());
                cAddressToSave.setCountry(cAddr.getCountry());

                SaveCompanyRequestDto companyToSave = new SaveCompanyRequestDto();
                companyToSave.setName(user.getCompany().getName());
                companyToSave.setDepartment(user.getCompany().getDepartment());
                companyToSave.setTitle(user.getCompany().getTitle());
                companyToSave.setUserid(user.getId());
                companyToSave.setAddress(cAddressToSave);

                companyService.saveCompany(companyToSave);
            }
            System.out.println("users saved...");
        }catch(Exception e){
            throw new RuntimeException("failed to save data" + e);
        }
    }

    @Async
    public void getAllUsers() {
        try {
            System.out.println("getting users");
            String url = "https://dummyjson.com/users";
            RestTemplate restTemplate = new RestTemplate();
            GetAllUsersDummyApiResponse resp = restTemplate.getForObject(
                    url, GetAllUsersDummyApiResponse.class
            );
            System.out.println("got response:");
            if (resp == null) throw new RuntimeException("no data");
            this.saveToDb(resp.getUsers());
        } catch (RuntimeException rx) {
            System.out.println(rx);
        }
    }
}
