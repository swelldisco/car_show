package com.example.car_show.owner;

import java.util.List;

import com.example.car_show.car.CarDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    
    private int ownerId;
    private String firstName;
    private String lastName;
    private List<CarDto> cars;

    public OwnerDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public OwnerDto(OwnerDto source) {
        this.ownerId = source.ownerId;
        this.firstName = source.firstName;
        this.lastName = source.lastName;
        this.cars = source.cars;
    }

    public int getId() {
        return ownerId;
    }

    public void setId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<CarDto> getCars() {
        return cars;
    }

    public void setCars(List<CarDto> cars) {
        this.cars = cars;
    }

}
