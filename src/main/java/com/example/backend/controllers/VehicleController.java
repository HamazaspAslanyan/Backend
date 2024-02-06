package com.example.backend.controllers;

import com.example.backend.services.VehicleService;
import com.example.backend.dtos.VehilceDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
public class VehicleController {

    private final VehicleService vehicleService;
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehilceDto>> allVehicles(){
        return ResponseEntity.ok(vehicleService.allVehicles());
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<VehilceDto> getVehicle(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.getVehicle(id));

    }

    @PostMapping("/vehicles")
    public ResponseEntity<VehilceDto> createVehicle(@Valid @RequestBody VehilceDto vehilceDto) {
        VehilceDto createdVehicle = vehicleService.createVehicle(vehilceDto);
        return ResponseEntity.created(URI.create("/vehicles/" + createdVehicle.getId())).body(createdVehicle);
    }

}
