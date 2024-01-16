package com.example.car_show.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    boolean existsByOwnerId(int id);

    @Transactional
    void deleteByOwnerId(int id);
    
}
