package com.example.backend.mappers;

import com.example.backend.dtos.VehilceDto;
import com.example.backend.entities.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toVehicle(VehilceDto vehilceDto);

    VehilceDto toVehicleDto(Vehicle vehicle);

    List<VehilceDto> toVehicleDtos(List<Vehicle> vehicles);
}
