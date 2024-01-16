package com.example.car_show.car;

import com.example.car_show.owner.Owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    
    private int carId;

    @NotNull(message = "Make cannot be null")
    @NotBlank(message = "Make cannot be left blank")
    @NotEmpty(message = "Make cannot be left empty")
    private String make;

    @NotNull(message = "Model cannot be null")
    @NotBlank(message = "Model cannot be left blank")
    @NotEmpty(message = "Model cannot be left empty")
    private String model;

    @NotNull(message = "Color cannot be null")
    @NotBlank(message = "Color cannot be left blank")
    @NotEmpty(message = "Color cannot be left empty")
    private String color;

    @NotNull(message = "Year cannot be null")
    @NotBlank(message = "Year cannot be left blank")
    @NotEmpty(message = "Year cannot be left empty")
    private String plateNumber;

    @NotNull(message = "Year cannot be null")
    private int year;

    @NotNull(message = "price cannot be null")
    private double price;

    private Owner carOwner;

    public CarDto(String make, String model, String color, String plateNumber, int year, double price) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.year = year;
        this.price = price;
    }

    public CarDto(String make, String model, String color, String plateNumber, int year, double price, Owner carOwner) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.year = year;
        this.price = price;
        this.carOwner = carOwner;
    }

    public CarDto(CarDto source) {
        this.carId = source.carId;
        this.make = source.make;
        this.model = source.model;
        this.color = source.color;
        this.plateNumber = source.plateNumber;
        this.year = source.year;
        this.price = source.price;
        this.carOwner = source.carOwner;
    }

    public int getCarId() {
        return carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Owner getOwner() {
        return carOwner;
    }

    public void setOwner(Owner carOwner) {
        this.carOwner = carOwner;
    }

    @Override
    public String toString() {
        return getColor() + " " + getYear() + " " + getMake() + " " + getModel(); 
    }
}
