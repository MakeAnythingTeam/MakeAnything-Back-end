package com.example.MakeAnything.domain.user.repository;

import com.example.MakeAnything.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    User findUserById(Long userId);

    Optional<User> findBySocialId(String socialId);

    Boolean existsBySocialId(String socialId);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
