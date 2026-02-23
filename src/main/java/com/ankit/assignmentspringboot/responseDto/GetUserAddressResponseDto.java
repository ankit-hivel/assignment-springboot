package com.ankit.assignmentspringboot.responseDto;

import com.ankit.assignmentspringboot.model.UserAddressModel;

public class GetUserAddressResponseDto {
    private String area;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;
    private float latitude;
    private float longitude;

    public GetUserAddressResponseDto(UserAddressModel userAddress) {
        this.area = userAddress.getArea();
        this.city = userAddress.getCity();
        this.state = userAddress.getState();
        this.stateCode = userAddress.getStateCode();
        this.postalCode = userAddress.getPostalCode();
        this.country = userAddress.getCountry();
        this.latitude = userAddress.getLatitude();
        this.longitude = userAddress.getLongitude();
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
