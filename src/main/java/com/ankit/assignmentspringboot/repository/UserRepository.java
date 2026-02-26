package com.ankit.assignmentspringboot.repository;

import com.ankit.assignmentspringboot.model.UserModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    @EntityGraph(attributePaths = {"company", "userAddress"})
    @NonNull Optional<UserModel> findById(Integer id);
}
