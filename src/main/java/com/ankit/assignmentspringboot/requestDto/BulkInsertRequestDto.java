package com.ankit.assignmentspringboot.requestDto;

import com.ankit.assignmentspringboot.utility.DateSerializer;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

public class BulkInsertRequestDto {
    private SaveUserRequestDto user;
    private SaveCompanyRequestDto company;
    private SaveUserAddressRequestDto address;

    public SaveUserRequestDto getUser() {
        return user;
    }

    public void setUser(SaveUserRequestDto user) {
        this.user = user;
    }

    public SaveCompanyRequestDto getCompany() {
        return company;
    }

    public void setCompany(SaveCompanyRequestDto company) {
        this.company = company;
    }

    public SaveUserAddressRequestDto getAddress() {
        return address;
    }

    public void setAddress(SaveUserAddressRequestDto address) {
        this.address = address;
    }
}
