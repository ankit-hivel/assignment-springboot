package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.responseDto.GetUserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
