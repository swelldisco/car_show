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
		Owner billy = new Owner("Billy", "Bob");
		Owner jim = new Owner("Jim", "Bob");
		List<Owner> owners = Arrays.asList(
			billy, jim
		);

		for (Owner owner : owners) {
			ownerRepo.save(owner);
		}

		
		List<CarDto> cars = Arrays.asList(
			new CarDto("Ford","Lighting","gray","FL-234",2023,75000, billy),
			new CarDto("Nissan","Leaf","green","BFG-345",2022,40000, billy),
			new CarDto("Toyota","Sienna","silver","CDF-233",2024,60000, jim),
			new CarDto("Honda","Accord","white","HW-345",2024,57000, jim)
        );

		for (CarDto dto : cars) {
			carService.createCar(dto);
		}
	 }

}
