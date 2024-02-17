package com.example.backend.services;

import com.example.backend.dtos.VehilceDto;
import com.example.backend.entities.Vehicle;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.VehicleMapper;
import com.example.backend.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public List<VehilceDto> allVehicles(){
        List <Vehicle> all = vehicleRepository.findAll();
        return vehicleMapper.toVehicleDtos(all);
    }

    public VehilceDto getVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehicle not found", HttpStatus.NOT_FOUND));
        return vehicleMapper.toVehicleDto(vehicle);
    }

    public VehilceDto createVehicle(VehilceDto vehilceDto) {
        Vehicle vehicle = vehicleMapper.toVehicle(vehilceDto);
        Vehicle createdVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleDto(createdVehicle);
    }

    public VehilceDto updateVehicle(Long id, VehilceDto vehilceDto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehicle not found", HttpStatus.NOT_FOUND));

        vehicleMapper.updateVehicle(vehicle, vehicleMapper.toVehicle(vehilceDto));

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toVehicleDto(updatedVehicle);
    }

    public VehilceDto deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new AppException("Vehicle not found", HttpStatus.NOT_FOUND));

        vehicleRepository.deleteById(id);

        return  vehicleMapper.toVehicleDto(vehicle);

    }
}
