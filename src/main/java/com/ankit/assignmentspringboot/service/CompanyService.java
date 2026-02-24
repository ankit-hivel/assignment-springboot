package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.CompanyAddressModel;
import com.ankit.assignmentspringboot.model.CompanyModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.CompanyAddressRepository;
import com.ankit.assignmentspringboot.repository.CompanyRepository;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.requestDto.SaveCompanyRequestDto;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.requestDto.UpdateCompanyRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetCompanyResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyAddressRepository companyAddressRepository;
    private final UserRepository userRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyAddressRepository companyAddressRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.companyAddressRepository = companyAddressRepository;
        this.userRepository = userRepository;
    }

    public void saveCompany(SaveCompanyRequestDto dto){
        // company address
        CompanyAddressModel cAddress = new CompanyAddressModel();
        cAddress.setArea(dto.getAddress().getArea());
        cAddress.setCity(dto.getAddress().getCity());
        cAddress.setCountry(dto.getAddress().getCountry());
        cAddress.setPostalCode(dto.getAddress().getPostalCode());
        cAddress.setLattitude(dto.getAddress().getLat());
        cAddress.setLongitude(dto.getAddress().getLng());
        cAddress.setState(dto.getAddress().getState());
        cAddress.setStateCode(dto.getAddress().getStateCode());

//        companyAddressRepository.save(cAddress);

        CompanyModel company = new CompanyModel();

        company.setDepartment(dto.getDepartment());
        company.setName(dto.getName());
        company.setTitle(dto.getTitle());

        UserModel userToRefer = userRepository.findById(dto.getUserid()).orElseThrow(
                ()-> new RuntimeException("user not found")
        );

        company.setUser(userToRefer);
        company.setCompanyAddress(cAddress);
        cAddress.setCompany(company);
        companyRepository.save(company);
    }

    public GetCompanyResponseDto getCompanyById (int id){
        CompanyModel company = companyRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("company doesn't exist")
        );
        System.out.println(company);
        return new GetCompanyResponseDto(company);
    }
    @Transactional
    public void updateCompanyById(UpdateCompanyRequestDto dto) {
        CompanyModel company = companyRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("company not found"));

        company.setTitle(dto.getTitle());
        company.setName(dto.getName());
        company.setDepartment(dto.getDepartment());

        CompanyAddressModel address = company.getCompanyAddress();

        address.setStateCode(dto.getAddress().getStateCode());
        address.setState(dto.getAddress().getState());
        address.setLongitude(dto.getAddress().getLng());
        address.setLattitude(dto.getAddress().getLat());
        address.setCity(dto.getAddress().getCity());
        address.setCountry(dto.getAddress().getCountry());
        address.setArea(dto.getAddress().getArea());
        address.setPostalCode(dto.getAddress().getPostalCode());
    }

    public void deleteCompanyById(int id) {
        companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("company not found")
        );
        companyRepository.deleteById(id);
    }
}
