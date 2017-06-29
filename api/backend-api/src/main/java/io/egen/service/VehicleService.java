package io.egen.service;

import io.egen.entity.Vehicle;
import io.egen.pojo.VehicleResult;

import java.util.List;

public interface VehicleService {
    List<VehicleResult> findAll(String sortParam, int total);

    VehicleResult findOne(String vin);

    Vehicle create(Vehicle veh);

    Vehicle update(String id, Vehicle veh);

    void upsert(List<Vehicle> vehicles);

    void delete(String vin);
}
