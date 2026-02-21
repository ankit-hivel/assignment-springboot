package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.UserAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressModel, Integer> {

}
