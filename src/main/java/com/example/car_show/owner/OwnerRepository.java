package com.example.car_show.owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    boolean existsByOwnerId(int id);
    
}
