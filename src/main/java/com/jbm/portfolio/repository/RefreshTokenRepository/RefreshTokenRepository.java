package com.jbm.portfolio.repository.RefreshTokenRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbm.portfolio.model.User;
import com.jbm.portfolio.model.RefreshToken.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}