package com.example.car_show.owner;

import java.util.ArrayList;
import java.util.List;

import com.example.car_show.car.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "_owner")
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carOwner")
    @JsonIgnore
    private List<Car> cars;

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cars = new ArrayList<>();
    }

    public Owner(Owner source) {
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
