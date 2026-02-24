package com.ankit.assignmentspringboot.responseDto;

import com.ankit.assignmentspringboot.model.CompanyModel;

import java.time.Instant;

public class GetCompanyResponseDto {
    private Integer id;
    private String department;
    private String name;
    private String title;
    private Instant createdAt;
    private Instant updatedAt;
    private String area;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;
    private Float lat;
    private Float lng;

    public GetCompanyResponseDto(
           CompanyModel company
    ) {
        this.id = company.getId();
        this.department = company.getDepartment();
        this.name = company.getName();
        this.title = company.getTitle();
        this.createdAt = company.getCreatedAt();
        this.updatedAt = company.getUpdatedAt();
        this.area = company.getCompanyAddress().getArea();
        this.city = company.getCompanyAddress().getCity();
        this.state = company.getCompanyAddress().getState();
        this.stateCode = company.getCompanyAddress().getStateCode();
        this.postalCode = company.getCompanyAddress().getPostalCode();
        this.country = company.getCompanyAddress().getCountry();
        this.lat = company.getCompanyAddress().getLattitude();
        this.lng = company.getCompanyAddress().getLongitude();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
