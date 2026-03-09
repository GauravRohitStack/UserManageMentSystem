//package com.jbm.portfolio.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import com.jbm.portfolio.model.User;
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    Optional<User> findByEmail(String email);
//
//    boolean existsByEmail(String email);
//}



package com.jbm.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jbm.portfolio.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}