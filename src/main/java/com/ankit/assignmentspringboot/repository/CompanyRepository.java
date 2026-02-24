package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, Integer> {
}
