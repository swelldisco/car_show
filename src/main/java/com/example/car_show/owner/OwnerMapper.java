package com.example.car_show.owner;

import java.util.ArrayList;
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
        if (source != null) {
            return source.stream()
            .map(c -> mapper.mapToCar(c))
            .toList();
        } else {
            return new ArrayList<Car>();
        }
    }

    private List<CarDto> mapListToCarDto(List<Car> source) {
        if (source != null) {
            return source.stream()
            .map(c -> mapper.mapToDto(c))
            .toList();
        } else {
            return new ArrayList<CarDto>();
        }
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
