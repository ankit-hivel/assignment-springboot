package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
