package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.CompanyAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAddressRepository extends JpaRepository<CompanyAddressModel, Integer> {
}
