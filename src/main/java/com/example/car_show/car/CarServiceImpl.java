package com.example.car_show.car;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.car_show.exceptions.ResourceNotFoundException;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository repo;
    private CarMapper mapper;
    public CarServiceImpl(CarRepository repo) {
        this.repo = repo;
        this.mapper = new CarMapper();
    }

    @Override
    public CarDto createCar(CarDto dto) {
        return mapper.mapToDto(repo.save(mapper.mapToCar(dto)));
    }

    @Override
    public CarDto findCarById(int carId) {
        return mapper.mapToDto(testOptionalCarById(carId));
    }

    @Override
    public List<CarDto> getAllCars() {
        return repo.findAll().stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
    }

    @Override
    public List<CarDto> getAllCarsByMake(String make) {
        return repo.getAllByMake(make).stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
    }

    @Override
    public List<CarDto> getAllCarsByModel(String model) {
        return repo.getAllByModel(model).stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
    }

    @Override
    public List<CarDto> getAllCarsByColor(String color) {
        return repo.getAllByColor(color).stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
    }

    @Override
    public List<CarDto> getAllCarsByYear(int year) {
        return repo.getAllByYear(year).stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
    }

    public CarDto updateCar(int carId, CarDto carDto) {
        Car updatedCar = testOptionalCarById(carId);
        updatedCar.setMake(carDto.getMake());
        updatedCar.setModel(carDto.getModel());
        updatedCar.setColor(carDto.getColor());
        updatedCar.setPlateNumber(carDto.getPlateNumber());
        updatedCar.setYear(carDto.getYear());
        updatedCar.setPrice(carDto.getPrice());
        updatedCar.setOwner(carDto.getOwner());
        
        return mapper.mapToDto(repo.save(updatedCar));
    }

    public void deleteCarById(int carId) {
        if (repo.existsByCarId(carId)) {
            repo.deleteByCarId(carId);
        } else {
            throw new ResourceNotFoundException("Car with " + carId + " not found.");
        }
    }

    
    private Car testOptionalCarById(int carId) {
        return repo.findById(carId)
            .orElseThrow(() -> new ResourceNotFoundException("Car with " + carId + " not found."));
    }
}
