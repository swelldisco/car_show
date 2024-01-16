package com.example.car_show.owner;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    
    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    // http://127.0.0.1:8085/api/v1/owner/create
    @PostMapping("/create")
    public ResponseEntity<OwnerDto> createUser(@RequestBody OwnerDto ownerDto) {
        return new ResponseEntity<>(service.createOwner(ownerDto), HttpStatus.CREATED);
    }

    // http://127.0.0.1:8085/api/v1/owner/1
    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable int ownerId) {
        return new ResponseEntity<>(service.getOwnerById(ownerId), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/owner/all
    @GetMapping("/all")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return new ResponseEntity<>(service.getAllOwners(), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/owner/1
    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable int ownerId, @RequestBody OwnerDto ownerDto) {
        return new ResponseEntity<>(service.updateOwner(ownerId, ownerDto), HttpStatus.ACCEPTED);
    }

    // http://127.0.0.1:8085/api/v1/owner/1
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<HttpStatus> deleteOwner(@PathVariable int ownerId) {
        service.deleteOwnerById(ownerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // http://127.0.0.1:8085/api/v1/owner/claim?carId=1
    @PutMapping("/claim")
    public ResponseEntity<OwnerDto> claimCar(@RequestParam int carId, int ownerId) {
        return new ResponseEntity<>(service.claimCar(carId, ownerId), HttpStatus.ACCEPTED);
    }

    // http://127.0.0.1:8085/api/v1/owner/ditch?carId=1
    @PutMapping("/ditch")
    public ResponseEntity<OwnerDto> ditchCar(@RequestParam int carId, int ownerId) {
        return new ResponseEntity<>(service.ditchCar(carId, ownerId), HttpStatus.ACCEPTED);
    }

    // http://127.0.0.1:8085/api/v1/owner/transfer?carId=1&giverId=1&receiverId=2
    @PutMapping("/transfer")
    public ResponseEntity<HttpStatus> transferCar(@RequestParam int carId, @RequestParam int giverId, @RequestParam int receiverId) {
        service.transferOwnership(carId, giverId, receiverId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    

}
