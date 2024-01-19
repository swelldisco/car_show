package com.example.car_show.car;

public class CarMapper {
    
    public CarDto mapToDto(Car source) {
        return new CarDto(
            source.getCarId(),
            source.getMake(),
            source.getModel(),
            source.getColor(),
            source.getPlateNumber(),
            source.getYear(),
            source.getPrice(),
            source.getOwner()
        );
    }

    public Car mapToCar(CarDto source) {
        return new Car(
            source.getCarId(),
            source.getMake(),
            source.getModel(),
            source.getColor(),
            source.getPlateNumber(),
            source.getYear(),
            source.getPrice(),
            source.getOwner()
        );
    }

    // public Car mapToCar(CarDto source) {
    //     return new Car(
    //         source.carId(),
    //         source.make(),
    //         source.model(),
    //         source.color(),
    //         source.plateNumber(),
    //         source.year(),
    //         source.price(),
    //         source.owner()
    //     );
    // }
}
