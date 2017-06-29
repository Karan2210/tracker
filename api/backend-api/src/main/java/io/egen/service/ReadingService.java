package io.egen.service;

import io.egen.entity.Reading;

import java.util.List;

public interface ReadingService {
    List<Reading> findAll();

    List<Reading> findReadingsFromVehicle(String vin, String filter);

    Reading create(Reading reading);

    void delete(String readingId);
}
