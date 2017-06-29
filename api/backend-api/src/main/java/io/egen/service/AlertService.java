package io.egen.service;

import io.egen.entity.Alert;

import java.util.List;

public interface AlertService {
    List<Alert> findAll();

    List<Alert> findAlertsFromVehicle(String vin);
}
