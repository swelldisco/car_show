package com.example.car_show.owner;

public class OwnerMapper {
    
    public Owner mapToOwner(OwnerDto source) {
        return new Owner(
            source.getId(),
            source.getFirstName(),
            source.getLastName(),
            source.getCars()
        );
    }

    public OwnerDto mapToDto(Owner source) {
        return new OwnerDto(
            source.getId(),
            source.getFirstName(),
            source.getLastName(),
            source.getCars()
        );
    }
    
}
