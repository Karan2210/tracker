package io.egen.service;

import io.egen.entity.Vehicle;
import io.egen.exception.NotFoundException;
import io.egen.pojo.VehicleResult;
import io.egen.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResult> findAll(String sortParam, int total) {
        return repository.findAll(sortParam, total);
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleResult findOne(String vin) {
        Vehicle vehicle = repository.findOne(vin);
        VehicleResult result;
        if (vehicle == null) {
            throw new NotFoundException("Vechile with vin=" + vin + " not found");
        } else {
            result = repository.findVehicleResult(vin);
        }
        return result;
    }

    @Override
    @Transactional
    public Vehicle create(Vehicle veh) {
        return repository.create(veh);
    }

    @Override
    @Transactional
    public Vehicle update(String vin, Vehicle veh) {
        Vehicle existing = repository.findOne(vin);
        if (existing == null) {
            throw new NotFoundException("Vehicle with vin=" + vin + " not found");
        }
        return repository.update(veh);
    }

    @Override
    @Transactional
    public void delete(String vin) {
        Vehicle existing = repository.findOne(vin);
        if (existing == null) {
            throw new NotFoundException("Vehicle with vin=" + vin + " not found");
        }
        repository.delete(existing);
    }

    @Override
    @Transactional
    public void upsert(List<Vehicle> vehicles) {
        for(Vehicle veh :vehicles) {
            Vehicle existing = repository.findOne(veh.getVin());
            if (existing == null) {
                repository.create(veh);
            } else {
                repository.update(veh);
            }
        }
    }
}
