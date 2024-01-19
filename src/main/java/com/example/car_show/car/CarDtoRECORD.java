package com.example.car_show.car;

import com.example.car_show.owner.Owner;

public record CarDtoRECORD(int carId, String make, String model, String color, String plateNumber, int year, double price, Owner owner) {}
