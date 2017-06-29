package io.egen.repository;

import io.egen.entity.Alert;

import java.util.List;

public interface AlertRepository {
    List<Alert> findAll();

    List<Alert> findAlertsFromVehicle(String vin);

    Alert create(Alert alert);
}
