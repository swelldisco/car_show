package com.example.car_show.car;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CarService {

    CarDto createCar(CarDto dto); 
    CarDto findCarById(int carId);
    List<CarDto> getAllCars();
    List<CarDto> getAllCarsByMake(String make);
    List<CarDto> getAllCarsByModel(String model);  
    List<CarDto> getAllCarsByColor(String color);
    List<CarDto> getAllCarsByYear(int year);
    CarDto updateCar(int carId, CarDto carDto);
    void deleteCarById(int carId);

}
