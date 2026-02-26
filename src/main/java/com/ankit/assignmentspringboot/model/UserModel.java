package com.ankit.assignmentspringboot.model;

import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.utility.UserRole;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "maiden_name")
    private String maidenName;

    private int age;

    private String gender;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "image_url")
    private String image;

    @Column(name = "blood_group")
    private String bloodGroup;

    private float height;     // centimeters
    private float weight;     // centimeters

    @Column(name = "eye_color")
    @ColumnTransformer(write = "lower(?)")
    private String eyeColor;

    @Column(name = "hair_color")
    @ColumnTransformer(write = "lower(?)")
    private String haircolor;

    @Column(name = "hair_type")
    @ColumnTransformer(write = "lower(?)")
    private String hairtype;

    @Column(name = "ip_address")
    private String ip;

    @Column(name = "mac_address")
    private String macAddress;

    private String university;

    private String ein;
    private String ssn;

    @Column(name = "user_agent")
    private String userAgent;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserAddressModel userAddress;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CompanyModel company;

    public UserAddressModel getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressModel userAddress) {
        this.userAddress = userAddress;
    }

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
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

    public UserModel(){}

    public UserModel(SaveUserRequestDto dto) {
        this.id = dto.getId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.maidenName = dto.getMaidenName();
        this.age = dto.getAge();
        this.gender = dto.getGender();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.username = dto.getUsername();
        this.password = dto.getPassword();   // ⚠ hash before saving in service
        this.birthDate = dto.getBirthDate();
        this.image = dto.getImage();
        this.bloodGroup = dto.getBloodGroup();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
        this.eyeColor = dto.getEyeColor();
        this.haircolor = dto.getHaircolor();
        this.hairtype = dto.getHairtype();
        this.ip = dto.getIp();
        this.macAddress = dto.getMacAddress();
        this.university = dto.getUniversity();
        this.ein = dto.getEin();
        this.ssn = dto.getSsn();
        this.userAgent = dto.getUserAgent();
        this.role = dto.getRole();
    }
}
