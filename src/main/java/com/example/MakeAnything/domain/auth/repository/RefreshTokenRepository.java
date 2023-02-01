package com.example.MakeAnything.domain.auth.repository;

import com.example.MakeAnything.domain.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
