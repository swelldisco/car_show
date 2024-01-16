package com.example.car_show.car;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // http://127.0.0.1:8085/api/v1/car/create
    @PostMapping("/create")
    public ResponseEntity<CarDto> createCar(@RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(service.createCar(carDto), HttpStatus.CREATED);
    }

    // http://127.0.0.1:8085/api/v1/car/all
    @GetMapping("/all")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return new ResponseEntity<>(service.getAllCars(), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/make?make=Honda
    @GetMapping("/make")
    public ResponseEntity<List<CarDto>> getAllCarsByMake(@RequestParam String make) {
        return new ResponseEntity<>(service.getAllCarsByMake(make), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/model?model=Leaf
    @GetMapping("/model")
    public ResponseEntity<List<CarDto>> getAllCarsByModel(@RequestParam String model) {
        return new ResponseEntity<>(service.getAllCarsByModel(model), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/color?color=Gray
    @GetMapping("/color")
    public ResponseEntity<List<CarDto>> getAllCarsBColor(@RequestParam String color) {
        return new ResponseEntity<>(service.getAllCarsByColor(color), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/year?year=2024
    @GetMapping("/year")
    public ResponseEntity<List<CarDto>> getAllCarsByYear(@RequestParam int year) {
        return new ResponseEntity<>(service.getAllCarsByYear(year), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/2
    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable int carId) {
        return new ResponseEntity<>(service.findCarById(carId), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/2
    @PutMapping("/{carId}")
    public ResponseEntity<CarDto> updateCar(@PathVariable int carId, @RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(service.updateCar(carId, carDto), HttpStatus.ACCEPTED);
    }

    // http://127.0.0.1:8085/api/v1/car/2
    @DeleteMapping("/{carId}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable int carId) {
        service.deleteCarById(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
