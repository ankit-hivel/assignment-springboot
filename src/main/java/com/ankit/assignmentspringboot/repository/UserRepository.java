package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query("select u.age, u.email from UserModel u")
    List<Object[]> findAllUsers();

}
