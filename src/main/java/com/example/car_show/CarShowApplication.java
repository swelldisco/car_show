package com.example.car_show;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.car_show.car.CarDto;
import com.example.car_show.car.CarService;
import com.example.car_show.owner.Owner;
import com.example.car_show.owner.OwnerRepository;


@SpringBootApplication
public class CarShowApplication implements CommandLineRunner {

	@Autowired
	private OwnerRepository ownerRepo;

	@Autowired
	private CarService carService;


	private static final Logger LOGGER = LoggerFactory.getLogger(CarShowApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CarShowApplication.class, args);
		LOGGER.info("Application Started");
	}
	@Override
	public void run(String... args) throws Exception {
		List<Owner> owners = Arrays.asList(
			new Owner("Billy", "Bob"),
			new Owner("Jim", "Bob")
		);

		for (Owner owner : owners) {
			ownerRepo.save(owner);
		}

		
		List<CarDto> cars = Arrays.asList(
			new CarDto("Ford","Lighting","Gray","FL-234",2023,75000),
			new CarDto("Nissan","Leaf","Green","BFG-345",2022,40000),
			new CarDto("Toyota","Sienna","Silver","CDF-233",2024,60000),
			new CarDto("Honda","Accord","White","HW-345",2024,57000)
        );

		for (CarDto dto : cars) {
			carService.createCar(dto);
		}
	 }

}
