package com.example.car_show.car;

import com.example.car_show.owner.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;

    @Column(name = "make")
    @Size(min = 3, max = 25, message = "Make must be between 3 and 25 characters.")
    private String make;

    @Column(name = "model")
    @Size(min = 3, max = 35, message = "Model must be between 3 and 35 characters.")
    private String model;

    @Column(name = "color")
    @Size(min = 3, max = 15, message = "Color must be between three and 15 characters.")
    private String color;

    @Column(name = "plateNo")
    private String plateNumber;

    @Column(name = "_year")
    private int year;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner carOwner;

    public Car(String make, String model, String color, String plateNumber, int year, double price) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.year = year;
        this.price = price;
    }

    public Car(String make, String model, String color, String plateNumber, int year, double price, Owner carOwner) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plateNumber = plateNumber;
        this.year = year;
        this.price = price;
        this.carOwner = carOwner;
    }

    public Car(Car source) {
        this.carId = source.carId;
        this.make = source.make;
        this.model = source.model;
        this.color = source.color;
        this.plateNumber = source.plateNumber;
        this.year = source.year;
        this.price = source.price;
        this.carOwner = source.carOwner;
    }

    public int getCardId() {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((make == null) ? 0 : make.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + year;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (make == null) {
            if (other.make != null)
                return false;
        } else if (!make.equals(other.make))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (year != other.year)
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        return true;
    }

    
    
}
