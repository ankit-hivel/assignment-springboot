package com.ankit.assignmentspringboot.responseDto;

import java.util.List;

public class GetAllUsersDummyApiResponse{
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}