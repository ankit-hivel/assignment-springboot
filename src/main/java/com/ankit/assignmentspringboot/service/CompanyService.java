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

        companyAddressRepository.save(cAddress);
        System.out.println("addr id: " + cAddress.getId());

        // company data
        CompanyModel company = new CompanyModel();
        System.out.println("created object");
        company.setDepartment(dto.getDepartment());
        company.setName(dto.getName());
        company.setTitle(dto.getTitle());

        System.out.println("userid: " + dto.getDepartment());
        UserModel userToRefer = userRepository.findById(dto.getUserid()).orElseThrow(
                ()-> new RuntimeException("user not found")
        );
        System.out.println("found user" + userToRefer);
        company.setUser(userToRefer);
        company.setCompanyAddress(cAddress);
        companyRepository.save(company);
        System.out.println("addr id: " + company.getId());
    }

    public GetCompanyResponseDto getCompanyById (int id){
        CompanyModel company = companyRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("company doesn't exist")
        );
        System.out.println(company);
        GetCompanyResponseDto companyResponse = new GetCompanyResponseDto(company);
        return companyResponse;
    }

    public void updateCompanyById(UpdateCompanyRequestDto dto) {
        CompanyModel companyToUpdate = companyRepository.findById(dto.getId()).orElseThrow(
                () -> new RuntimeException("company not found")
        );
        System.out.println("company found" + companyToUpdate);

        CompanyAddressModel cAddressToUpdate = companyToUpdate.getCompanyAddress();
        System.out.println(cAddressToUpdate.getArea());
        cAddressToUpdate.setStateCode(dto.getAddress().getStateCode());
        cAddressToUpdate.setState(dto.getAddress().getState());
        cAddressToUpdate.setLongitude(dto.getAddress().getLng());
        cAddressToUpdate.setLattitude(dto.getAddress().getLat());
        cAddressToUpdate.setCity(dto.getAddress().getCity());
        cAddressToUpdate.setCountry(dto.getAddress().getCountry());
        cAddressToUpdate.setArea(dto.getAddress().getArea());
        cAddressToUpdate.setPostalCode(dto.getAddress().getPostalCode());
        System.out.println(cAddressToUpdate.getId());
        System.out.println(cAddressToUpdate.getArea());
        companyAddressRepository.save(cAddressToUpdate);

        companyToUpdate.setCompanyAddress(cAddressToUpdate);
        companyToUpdate.setTitle(dto.getTitle());
        companyToUpdate.setName(dto.getName());
        companyToUpdate.setDepartment(dto.getDepartment());
        companyRepository.save(companyToUpdate);
    }

    public void deleteCompanyById(int id) {
        CompanyModel company = companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("company not found")
        );
        companyAddressRepository.deleteById(company.getCompanyAddress().getId());
        companyRepository.deleteById(id);
    }
}
