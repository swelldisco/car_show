package com.example.car_show.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    
    boolean existsByCarId(int cardId);
    List<Car> getAllByYear(int year);
    List<Car> getAllByMake(String make);
    List<Car> getAllByModel(String model);
    List<Car> getAllByColor(String color);

    @Transactional
    void deleteByCarId(int carId);

}
