package io.egen.controller;


import io.egen.entity.Reading;
import io.egen.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:8100","http://mocker.egen.io"}, maxAge = 3600)
@RestController
@RequestMapping(value = "readings")
public class ReadingController {

    @Autowired
    private ReadingService service;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{vin}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reading> findOne(@PathVariable("vin") String vin, @RequestParam(value="filter",required = false , defaultValue="normal") String filter) {
        return service.findReadingsFromVehicle(vin, filter);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void create( @RequestBody Reading reading) {
        service.create(reading);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{readingId}")
    public void delete(@PathVariable("readingId") String readingId) {
        service.delete(readingId);
    }
}
