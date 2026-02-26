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
import com.ankit.assignmentspringboot.utility.CONSTANTS;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class CompanyService {
    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    private final RedisService redis;
    private final ObjectMapper objectMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository, RedisService redis, ObjectMapper objectMapper) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.redis = redis;
        this.objectMapper = objectMapper;
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
        String redisKey = CONSTANTS.getCompanyRedisKey(id);
        String redisValue = redis.get(redisKey);
        log.info("got redis value -> " + redisValue);
        if (redisValue != null){
            log.info("cache hit");
            return objectMapper.readValue(redisValue, GetCompanyResponseDto.class);
        }
        log.info("cache miss");
        CompanyModel company = companyRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("company doesn't exist")
        );
        log.info("got company data");
        String jsonToWrite = objectMapper.writeValueAsString(new GetCompanyResponseDto(company));
        redis.saveWithTTL(redisKey, jsonToWrite, 5, TimeUnit.MINUTES);
        return new GetCompanyResponseDto(company);
    }

    @Transactional
    public void updateCompanyById(UpdateCompanyRequestDto dto) {
        CompanyModel company = companyRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("company not found"));
        log.info("company user id -> " + company.getUser().getId());

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

        String redisKey = CONSTANTS.getCompanyRedisKey(dto.getId());
        String jsonToWrite = objectMapper.writeValueAsString(new GetCompanyResponseDto(company));
        redis.saveWithTTL(redisKey, jsonToWrite, 5, TimeUnit.MINUTES);
        log.info("cache updated");
    }

    public void deleteCompanyById(int id) {
        companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("company not found")
        );
        companyRepository.deleteById(id);
        log.info("company deleted successfully");
    }
}
