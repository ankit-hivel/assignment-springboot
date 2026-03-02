package com.ankit.assignmentspringboot.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ankit.assignmentspringboot.model.CompanyAddressModel;
import com.ankit.assignmentspringboot.model.CompanyModel;
import com.ankit.assignmentspringboot.model.UserAddressModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.CompanyRepository;
import com.ankit.assignmentspringboot.repository.UserAddressRepository;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.BulkInsertRequestDto;
import com.ankit.assignmentspringboot.requestDto.CompanyAddressDto;
import com.ankit.assignmentspringboot.requestDto.SaveCompanyRequestDto;
import com.ankit.assignmentspringboot.requestDto.SaveUserAddressRequestDto;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.utility.GetAuthUserId;
import com.ankit.assignmentspringboot.utility.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulkInsertService {

    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final CompanyRepository companyRepository;

    public BulkInsertService(
            UserRepository userRepository,
            UserAddressRepository userAddressRepository,
            CompanyRepository companyRepository
    ) {
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void insertData(List<BulkInsertRequestDto> data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        for (BulkInsertRequestDto dto : data) {
            if (dto == null) {
                continue;
            }

            SaveUserRequestDto userDto = dto.getUser();
            if (userDto == null) {
                continue;
            }

            // user
            UserModel user = new UserModel(userDto);

            String rawPassword = user.getPassword();
            if (rawPassword != null && !rawPassword.isBlank()) {
                String hashedPassword = BCrypt.withDefaults()
                        .hashToString(12, rawPassword.toCharArray());
                user.setPassword(hashedPassword);
            }

            user.setRole(UserRole.USER);

            userRepository.save(user);

            user.setCreatedBy(GetAuthUserId.getUserId() != null ? GetAuthUserId.getUserId() : user.getId());
            user.setUpdatedBy(GetAuthUserId.getUserId() != null ? GetAuthUserId.getUserId() : user.getId());

            // user address
            SaveUserAddressRequestDto addrDto = dto.getAddress();
            if (addrDto != null) {
                UserAddressModel address = new UserAddressModel(addrDto);
                address.setUser(user);
                address.setCreatedBy(GetAuthUserId.getUserId() != null ? GetAuthUserId.getUserId() : user.getId());
                address.setUpdatedBy(GetAuthUserId.getUserId() != null ? GetAuthUserId.getUserId() : user.getId());

                userAddressRepository.save(address);
                user.setUserAddress(address);
            }

            // company
            SaveCompanyRequestDto compDto = dto.getCompany();
            if (compDto != null) {
                CompanyAddressDto cAddrDto = compDto.getAddress();

                CompanyAddressModel cAddress = new CompanyAddressModel();
                if (cAddrDto != null) {
                    cAddress.setArea(cAddrDto.getArea());
                    cAddress.setCity(cAddrDto.getCity());
                    cAddress.setCountry(cAddrDto.getCountry());
                    cAddress.setPostalCode(cAddrDto.getPostalCode());
                    cAddress.setLattitude(cAddrDto.getLat() != null ? cAddrDto.getLat() : 0f);
                    cAddress.setLongitude(cAddrDto.getLng() != null ? cAddrDto.getLng() : 0f);
                    cAddress.setState(cAddrDto.getState());
                    cAddress.setStateCode(cAddrDto.getStateCode());
                }

                CompanyModel company = new CompanyModel();
                company.setDepartment(compDto.getDepartment());
                company.setName(compDto.getName());
                company.setTitle(compDto.getTitle());

                company.setUser(user);
                company.setCompanyAddress(cAddress);
                cAddress.setCompany(company);

                company.setCreatedBy(GetAuthUserId.getUserId() != null ? GetAuthUserId.getUserId() : user.getId());
                company.setUpdatedBy(GetAuthUserId.getUserId() != null ? GetAuthUserId.getUserId() : user.getId());

                companyRepository.save(company);
                user.setCompany(company);
            }
        }
    }

    @Transactional
    public void deleteUsersByIds(List<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return;
        }
        userRepository.deleteAllById(userIds);
    }
}
