package com.example.car_show.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(long userId);
    Optional<User> findUserByEmailAddressIgnoringCase(String emailAddress);

    @Transactional
    void deleteByUserId(long userId);

}
