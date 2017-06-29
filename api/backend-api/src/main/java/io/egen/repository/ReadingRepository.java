package io.egen.repository;

import java.util.List;
import io.egen.entity.Reading;

public interface ReadingRepository {
    List<Reading> findAll();

    Reading findOne(String readingId);

    List<Reading> findReadingsFromVehicle(String vin, String filter);

    Reading create(Reading reading);

    void delete(Reading reading);
}
