package com.ankit.assignmentspringboot.responseDto;

import com.ankit.assignmentspringboot.model.CompanyAddressModel;
import com.ankit.assignmentspringboot.model.CompanyModel;
import com.ankit.assignmentspringboot.model.UserAddressModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.utility.UserRole;

import java.time.LocalDate;

public class UserExportDataToCsvDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
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
    private Boolean isDeleted;

    // user address
    private String area;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;
    private float latitude;
    private float longitude;

    // company
    private Integer companyId;
    private String department;
    private String name;
    private String title;

    // company address
    private String companyArea;
    private String companyCity;
    private String companyState;
    private String companyStateCode;
    private String companyPostalCode;
    private String companyCountry;
    private float companyLattitude;
    private float companyLongitude;

    public UserExportDataToCsvDto(UserModel user,
                                  UserAddressModel userAddress,
                                  CompanyModel company,
                                  CompanyAddressModel companyAddress) {

        // ===== User =====
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.maidenName = user.getMaidenName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.username = user.getUsername();
        this.password = user.getPassword();
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
        this.isDeleted = user.getDeleted();

        // ===== User Address =====
        if (userAddress != null) {
            this.area = userAddress.getArea();
            this.city = userAddress.getCity();
            this.state = userAddress.getState();
            this.stateCode = userAddress.getStateCode();
            this.postalCode = userAddress.getPostalCode();
            this.country = userAddress.getCountry();
            this.latitude = userAddress.getLatitude();
            this.longitude = userAddress.getLongitude();
        }

        // ===== Company =====
        if (company != null) {
            this.companyId = company.getId();
            this.department = company.getDepartment();
            this.name = company.getName();
            this.title = company.getTitle();
        }

        // ===== Company Address =====
        if (companyAddress != null) {
            this.companyArea = companyAddress.getArea();
            this.companyCity = companyAddress.getCity();
            this.companyState = companyAddress.getState();
            this.companyStateCode = companyAddress.getStateCode();
            this.companyPostalCode = companyAddress.getPostalCode();
            this.companyCountry = companyAddress.getCountry();
            this.companyLattitude = companyAddress.getLattitude();
            this.companyLongitude = companyAddress.getLongitude();
        }
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompanyStateCode() {
        return companyStateCode;
    }

    public void setCompanyStateCode(String companyStateCode) {
        this.companyStateCode = companyStateCode;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public float getCompanyLattitude() {
        return companyLattitude;
    }

    public void setCompanyLattitude(float companyLattitude) {
        this.companyLattitude = companyLattitude;
    }

    public float getCompanyLongitude() {
        return companyLongitude;
    }

    public void setCompanyLongitude(float companyLongitude) {
        this.companyLongitude = companyLongitude;
    }
}
