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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Car show Car REST API", description = "Crud operation REST api for the car object in car show program")
@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    // http://127.0.0.1:8085/api/v1/car/create
    @Operation(summary = "Create new cars.",
        description = "Create new cars by passing a valid carDto object and save them to the database.")
    @ApiResponse(responseCode = "201",
        description = "HTTP STATUS 201 CREATED")
    @PostMapping("/create")
    public ResponseEntity<CarDto> createCar(@RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(service.createCar(carDto), HttpStatus.CREATED);
    }

    // http://127.0.0.1:8085/api/v1/car/all
    @Operation(summary = "Get all cars.",
        description = "Retrieves all cars currently in the database.")
    @ApiResponse(responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/all")
    public ResponseEntity<List<CarDto>> getAllCars() {
        return new ResponseEntity<>(service.getAllCars(), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/make?make=Honda
    @Operation(summary = "Get all cars by make.",
        description = "Retrieves all cars currently in the database with the same make.")
    @ApiResponse(responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/make")
    public ResponseEntity<List<CarDto>> getAllCarsByMake(@RequestParam String make) {
        return new ResponseEntity<>(service.getAllCarsByMake(make), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/model?model=Leaf
    @Operation(summary = "Get all cars by model.",
        description = "Retrieves all cars currently in the database with the same model.")
    @ApiResponse(responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/model")
    public ResponseEntity<List<CarDto>> getAllCarsByModel(@RequestParam String model) {
        return new ResponseEntity<>(service.getAllCarsByModel(model), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/color?color=Gray
    @Operation(summary = "Get all cars by color.",
        description = "Retrieves all cars currently in the database with the same color.")
    @ApiResponse(responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/color")
    public ResponseEntity<List<CarDto>> getAllCarsBColor(@RequestParam String color) {
        return new ResponseEntity<>(service.getAllCarsByColor(color), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/year?year=2024
    @Operation(summary = "Get all cars by year.",
        description = "Retrieves all cars currently in the database with the same model year.")
    @ApiResponse(responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/year")
    public ResponseEntity<List<CarDto>> getAllCarsByYear(@RequestParam int year) {
        return new ResponseEntity<>(service.getAllCarsByYear(year), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/2
    @Operation(summary = "Get a car by id.",
        description = "Retrieves a car from the database based on its id.")
    @ApiResponse(responseCode = "200",
        description = "HTTP STATUS 200 OK")
    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable int carId) {
        return new ResponseEntity<>(service.findCarById(carId), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/car/2
    @PutMapping("/{carId}")
    @Operation(summary = "Update a car.",
        description = "Update a car based on its id and a valid CarDto object")
    @ApiResponse(responseCode = "202",
        description = "HTTP STATUS 202 OK")
    public ResponseEntity<CarDto> updateCar(@PathVariable int carId, @RequestBody @Valid CarDto carDto) {
        return new ResponseEntity<>(service.updateCar(carId, carDto), HttpStatus.ACCEPTED);
    }

    // http://127.0.0.1:8085/api/v1/car/2
    @Operation(summary = "Delete a car.",
        description = "Delete a car based on its id.")
    @ApiResponse(responseCode = "204",
        description = "HTTP STATUS 204 NO CONTENT")
    @DeleteMapping("/{carId}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable int carId) {
        service.deleteCarById(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
