package io.egen.repository;

import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Reading> findAll() {
        TypedQuery<Reading> query = em.createNamedQuery("Reading.findAll", Reading.class);
        return query.getResultList();
    }

    @Override
    public List<Reading> findReadingsFromVehicle(String vin, String filter) {
        TypedQuery<Reading> query = em.createNamedQuery("Reading.findByVin", Reading.class);
        if(filter.equalsIgnoreCase("last1d")) {
            query = em.createNamedQuery("Reading.findByVinWithFilter", Reading.class);
            query.setParameter("pDate", new Date(System.currentTimeMillis() - 24*60*60*1000));
        } else if (filter.equalsIgnoreCase("last30min")) {
            query = em.createNamedQuery("Reading.findByVinWithFilter", Reading.class);
            query.setParameter("pDate", new Date(System.currentTimeMillis() - 30*60*1000));
        } else if (filter.equalsIgnoreCase("last1hr")) {
            query = em.createNamedQuery("Reading.findByVinWithFilter", Reading.class);
            query.setParameter("pDate", new Date(System.currentTimeMillis() - 60*60*1000));
        } else if (filter.equalsIgnoreCase("last2hr")) {
            query = em.createNamedQuery("Reading.findByVinWithFilter", Reading.class);
            query.setParameter("pDate", new Date(System.currentTimeMillis() - 2*60*60*1000));
        }
        query.setParameter("pVin", vin);
        return query.getResultList();
    }

    @Override
    public Reading findOne(String readingId) {
        return em.find(Reading.class, readingId);
    }

    @Override
    public Reading create(Reading reading) {
        em.persist(reading.getTires());
        em.persist(reading);
        return reading;
    }

    @Override
    public void delete(Reading reading) {
        em.remove(reading);
    }
}
