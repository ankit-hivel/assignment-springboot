package com.ankit.assignmentspringboot.requestDto;

public class SaveCompanyRequestDto {
    private String department;
    private String name;
    private String title;

    private String userid;

    private CompanyAddressDto address;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyAddressDto getAddress() {
        return address;
    }

    public void setAddress(CompanyAddressDto address) {
        this.address = address;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
