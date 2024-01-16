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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {
    
    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    // http://127.0.0.1:8085/api/v1/owners/create
    @PostMapping("/create")
    public ResponseEntity<OwnerDto> createUser(@RequestBody OwnerDto ownerDto) {
        return new ResponseEntity<>(service.createOwner(ownerDto), HttpStatus.CREATED);
    }

    // http://127.0.0.1:8085/api/v1/owners/1
    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable int ownerId) {
        return new ResponseEntity<>(service.getOwnerById(ownerId), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/owners/all
    @GetMapping("/all")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        return new ResponseEntity<>(service.getAllOwners(), HttpStatus.OK);
    }

    // http://127.0.0.1:8085/api/v1/owners/1
    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable int ownerId, @RequestBody OwnerDto ownerDto) {
        return new ResponseEntity<>(service.updateOwner(ownerId, ownerDto), HttpStatus.ACCEPTED);
    }

    // http://127.0.0.1:8085/api/v1/owners/1
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<HttpStatus> deleteOwner(@PathVariable int ownerId) {
        service.deleteOwnerById(ownerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}
