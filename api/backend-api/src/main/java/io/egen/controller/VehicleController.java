package io.egen.controller;

import io.egen.entity.Vehicle;
import io.egen.pojo.VehicleResult;
import io.egen.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ={ "http://localhost:8100","http://mocker.egen.io"}, maxAge = 3600)
@RestController
@RequestMapping(value = "vehicles")
public class VehicleController {
    @Autowired
    private VehicleService service;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<VehicleResult> findAll(@RequestParam(value="qSort",required = false , defaultValue="desc") String sortParam,
                                       @RequestParam(value="total",required = false , defaultValue="50") int total) {
        return service.findAll(sortParam, total);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VehicleResult findOne(@PathVariable("vin") String vehId) {
        return service.findOne(vehId);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void update( @RequestBody List<Vehicle> vehicles) {
        service.upsert(vehicles);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{vin}")
    public void delete(@PathVariable("vin") String vehId) {
        service.delete(vehId);
    }
}
