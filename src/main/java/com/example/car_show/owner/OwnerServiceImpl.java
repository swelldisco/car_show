package com.example.car_show.owner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.car_show.car.Car;
import com.example.car_show.car.CarMapper;
import com.example.car_show.car.CarService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OwnerServiceImpl implements OwnerService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerServiceImpl.class);

    private final OwnerRepository repo;
    private OwnerMapper mapper;
    private CarService carSerice;
    private CarMapper carMapper;

    public OwnerServiceImpl(OwnerRepository repo) {
        this.repo = repo;
        mapper = new OwnerMapper();
    }

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) {
        return mapper.mapToDto(repo.save(mapper.mapToOwner(ownerDto)));
    }

    @Override
    public OwnerDto getOwnerById(int ownerId) {
        return mapper.mapToDto(checkOptionalOwnerById(ownerId));
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        return repo.findAll().stream()
            .map(o -> mapper.mapToDto(o))
            .toList();
    }

    @Override
    public OwnerDto updateOwner(int ownerId, OwnerDto ownerDto) {
        Owner updatedOwner = checkOptionalOwnerById(ownerId);
        updatedOwner.setFirstName(ownerDto.getFirstName());
        updatedOwner.setLastName(ownerDto.getLastName());
        return mapper.mapToDto(repo.save(updatedOwner));
    }

    @Override
    public void deleteOwnerById(int ownerId) {
        if (repo.existsByOwnerId(ownerId)) {
            deleteOwnerById(ownerId);
        } else {
            throw new EntityNotFoundException("Owner with id " + ownerId + " does not exist.");
        }
    }

    @Override
    public OwnerDto claimCar(int carId, int ownerId) {
        Owner owner = checkOptionalOwnerById(ownerId);
        Car car = carMapper.mapToCar(carSerice.findCarById(carId));
        if (car.getOwner() == null) {
            owner.addCarToList(car);
            repo.save(owner);
        } else {
            LOGGER.info("need to throw an approprate error here: you cannot claim a car that someone else already owns!");
        }
        return mapper.mapToDto(owner);
    }

    @Override
    public OwnerDto ditchCar(int carId, int ownerId) {
        Owner owner = checkOptionalOwnerById(ownerId);
        owner.removeCarFromList(carMapper.mapToCar(carSerice.findCarById(carId)));
        repo.save(owner);
        return mapper.mapToDto(owner);
    }

    public void transferOwnership(int carId, int giverId, int receiverId) {
        Car car = carMapper.mapToCar(carSerice.findCarById(carId));
        if (car.getOwner().getId() != giverId) {
            LOGGER.info("Need to throw an appropriate error here: you cannot give away a car you don't own!");
        } else {
            Owner givingOwner = checkOptionalOwnerById(giverId);
            Owner receivingOwner = checkOptionalOwnerById(receiverId);
            givingOwner.removeCarFromList(car);
            receivingOwner.addCarToList(car);
        }
    }

    private Owner checkOptionalOwnerById(int ownerId) {
        return repo.findById(ownerId)
            .orElseThrow(() -> new NullPointerException());
    }

}
