package com.example.car_show.car;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @PutMapping("/create")
    public ResponseEntity<CarDto> createCar(@RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(service.createCar(carDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return new ResponseEntity<>(service.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/make")
    public ResponseEntity<List<CarDto>> getAllCarsByMake(@RequestParam String make) {
        return new ResponseEntity<>(service.getAllCarsByMake(make), HttpStatus.OK);
    }

    @GetMapping("/model")
    public ResponseEntity<List<CarDto>> getAllCarsByModel(@RequestParam String model) {
        return new ResponseEntity<>(service.getAllCarsByModel(model), HttpStatus.OK);
    }

    @GetMapping("/color")
    public ResponseEntity<List<CarDto>> getAllCarsBColor(@RequestParam String color) {
        return new ResponseEntity<>(service.getAllCarsByColor(color), HttpStatus.OK);
    }

    @GetMapping("/year")
    public ResponseEntity<List<CarDto>> getAllCarsByYear(@RequestParam int year) {
        return new ResponseEntity<>(service.getAllCarsByYear(year), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<CarDto> getCarById(@RequestParam int carId) {
        return new ResponseEntity<>(service.findCarById(carId), HttpStatus.OK);
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<CarDto> updateCar(@PathVariable int carId, @RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(service.updateCar(carId, carDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable int carId) {
        service.deleteCarById(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
