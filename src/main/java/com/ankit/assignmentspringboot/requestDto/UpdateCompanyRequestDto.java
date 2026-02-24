package com.ankit.assignmentspringboot.requestDto;

public class UpdateCompanyRequestDto extends SaveCompanyRequestDto {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
