package com.example.car_show.owner;

import java.util.List;

import com.example.car_show.car.Car;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    
    private int ownerId;
    private String firstName;
    private String lastName;
    private List<Car> cars;

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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
