package io.egen.controller;

import io.egen.entity.Alert;
import io.egen.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:8100","http://mocker.egen.io"}, maxAge = 3600)
@RestController
@RequestMapping(value = "alerts")
public class AlertController {

    @Autowired
    private AlertService service;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findOne(@PathVariable("vin") String vin) {
        return service.findAlertsFromVehicle(vin);
    }

}
