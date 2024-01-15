package com.example.car_show.owner;

import java.util.List;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOwner'");
    }

    @Override
    public void deleteOwnerById(int ownerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOwnerById'");
    }

    private Owner checkOptionalOwnerById(int ownerId) {
        return repo.findById(ownerId)
            .orElseThrow(() -> new NullPointerException());
    }

}
