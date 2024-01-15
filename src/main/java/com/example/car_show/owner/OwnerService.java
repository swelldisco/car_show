package com.example.car_show.owner;

import java.util.List;

public interface OwnerService {

    OwnerDto createOwner (OwnerDto ownerDto);
    OwnerDto getOwnerById (int ownerId);
    List<OwnerDto> getAllOwners ();
    OwnerDto updateOwner(int ownerId, OwnerDto ownerDto);
    void deleteOwnerById(int ownerId);
    
}
