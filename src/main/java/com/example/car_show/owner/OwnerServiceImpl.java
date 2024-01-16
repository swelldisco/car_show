package com.example.car_show.owner;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OwnerServiceImpl implements OwnerService{
    
    private final OwnerRepository repo;
    private OwnerMapper mapper;

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

    private Owner checkOptionalOwnerById(int ownerId) {
        return repo.findById(ownerId)
            .orElseThrow(() -> new NullPointerException());
    }

}
