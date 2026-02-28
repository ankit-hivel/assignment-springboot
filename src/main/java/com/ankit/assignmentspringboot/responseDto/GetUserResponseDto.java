package com.ankit.assignmentspringboot.responseDto;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.utility.UserRole;

import java.time.LocalDate;

public class GetUserResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private LocalDate birthDate;
    private String image;
    private String bloodGroup;
    private float height;
    private float weight;
    private String eyeColor;
    private String haircolor;
    private String hairtype;
    private String ip;
    private String macAddress;
    private String university;
    private String ein;
    private String ssn;
    private String userAgent;
    private UserRole role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(String haircolor) {
        this.haircolor = haircolor;
    }

    public String getHairtype() {
        return hairtype;
    }

    public void setHairtype(String hairtype) {
        this.hairtype = hairtype;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public GetUserResponseDto(){}

    public GetUserResponseDto(UserModel user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.maidenName = user.getMaidenName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.username = user.getUsername();
        this.birthDate = user.getBirthDate();
        this.image = user.getImage();
        this.bloodGroup = user.getBloodGroup();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.eyeColor = user.getEyeColor();
        this.haircolor = user.getHaircolor();
        this.hairtype = user.getHairtype();
        this.ip = user.getIp();
        this.macAddress = user.getMacAddress();
        this.university = user.getUniversity();
        this.ein = user.getEin();
        this.ssn = user.getSsn();
        this.userAgent = user.getUserAgent();
        this.role = user.getRole();
    }
}
