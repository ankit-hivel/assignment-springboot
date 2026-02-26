package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.UserModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    @EntityGraph(attributePaths = {"company", "userAddress"})
    @NonNull Optional<UserModel> findById(String id);

    @EntityGraph(attributePaths = {"company", "company.companyAddress", "userAddress"})
    @NonNull List<UserModel> findAll();

    Optional<UserModel> findByEmailIgnoreCaseAndIsDeleted(String email, boolean isDeleted);

    Optional<UserModel> findByIdAndIsDeleted(String id, boolean isDeleted);

    Page<UserModel> findAllByIsDeleted(boolean isDeleted, Pageable pageable);
}
