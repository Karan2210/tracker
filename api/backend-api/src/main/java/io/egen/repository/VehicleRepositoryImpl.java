package io.egen.repository;

import io.egen.entity.Vehicle;
import io.egen.pojo.VehicleResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<VehicleResult> findAll(String sortParam, int total) {

        String hql = "select v.vin as vin, v.make as make, v.model as model, v.year as year, v.redlineRpm as redlineRpm," +
                "v.maxFuelVolume as maxFuelVolume, v.lastServiceDate as lastServiceDate, count(a.vin) as highAlertCount " +
                "from Vehicle as v left join (select * from Alert where alertPriority='HIGH' and alertCreationTime >= :date ) as a " +
                "on v.vin = a.vin " +
                "group by v.vin " +
                "order by count(*) " + sortParam;
        Query query = em.createNativeQuery(hql);
        query.setParameter("date", new Date(System.currentTimeMillis() - 2*60*60*1000));
        List<VehicleResult> results = new ArrayList<VehicleResult>();
        for(Object result: query.getResultList()) {
            VehicleResult temp = new VehicleResult();
            Object[] tuple = (Object[])result;
            temp.setVin((String)tuple[0]);
            temp.setMake((String)tuple[1]);
            temp.setModel((String)tuple[2]);
            temp.setYear((int)tuple[3]);
            temp.setRedlineRpm((int)tuple[4]);
            temp.setMaxFuelVolume((float)tuple[5]);
            temp.setLastServiceDate((Date)tuple[6]);
            temp.setHighAlertCount(((BigInteger)tuple[7]));
            results.add(temp);
        }
        return results;
    }

    @Override
    public VehicleResult findVehicleResult(String vin) {

        String hql = "select v.vin as vin, v.make as make, v.model as model, v.year as year, v.redlineRpm as redlineRpm," +
                "v.maxFuelVolume as maxFuelVolume, v.lastServiceDate as lastServiceDate, count(a.vin) as highAlertCount " +
                "from Vehicle as v left join (select * from Alert where alertPriority='HIGH' and alertCreationTime >= :date ) as a " +
                "on v.vin = a.vin " +
                "group by v.vin having v.vin=:vin";
        Query query = em.createNativeQuery(hql);
        query.setParameter("date", new Date(System.currentTimeMillis() - 2*60*60*1000));
        query.setParameter("vin", vin);
        VehicleResult temp= null;
        for(Object result: query.getResultList()) {
            temp  = new VehicleResult();
            Object[] tuple = (Object[])result;
            temp.setVin((String)tuple[0]);
            temp.setMake((String)tuple[1]);
            temp.setModel((String)tuple[2]);
            temp.setYear((int)tuple[3]);
            temp.setRedlineRpm((int)tuple[4]);
            temp.setMaxFuelVolume((float)tuple[5]);
            temp.setLastServiceDate((Date)tuple[6]);
            temp.setHighAlertCount((BigInteger) tuple[7]);
        }
        return temp;
    }

    @Override
    public Vehicle findOne(String vin) {
        return em.find(Vehicle.class, vin);
    }

    @Override
    public Vehicle create(Vehicle veh) {
        em.persist(veh);
        return veh;
    }

    @Override
    public Vehicle update(Vehicle veh) {
        return em.merge(veh);
    }

    @Override
    public void delete(Vehicle veh) {
        em.remove(veh);
    }
}
