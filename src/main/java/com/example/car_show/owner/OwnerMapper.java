package com.example.car_show.owner;

import java.util.List;

import com.example.car_show.car.Car;
import com.example.car_show.car.CarDto;
import com.example.car_show.car.CarMapper;

public class OwnerMapper {

    private CarMapper mapper;

    public OwnerMapper() {
        this.mapper = new CarMapper();
    }

    private List<Car> mapListToCar(List<CarDto> source) {
        return source.stream()
            .map(c -> mapper.mapToCar(c))
            .toList();
    }

    private List<CarDto> mapListToCarDto(List<Car> source) {
        return source.stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
    }
    
    public Owner mapToOwner(OwnerDto source) {
        return new Owner(
            source.getId(),
            source.getFirstName(),
            source.getLastName(),
            mapListToCar(source.getCars())
        );
    }

    public OwnerDto mapToDto(Owner source) {
        return new OwnerDto(
            source.getId(),
            source.getFirstName(),
            source.getLastName(),
            mapListToCarDto(source.getCars())
        );
    }
    
}
