package com.example.apkabazadanych2.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u where u.login = ?1")
    Optional<User> findByLogin(String login);

    @Query("SELECT u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);
}
