package com.temperatures.controller;


import com.temperatures.model.Temperature;
import com.temperatures.service.TemperatureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemperatureController {


    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping("/temperatures")
    public List<Temperature> getTemperatures(){
        return  this.temperatureService.getTemperatures();
    }


    @PostMapping("/temperatures")
    public Temperature postTemperature(@RequestBody Temperature newTemperature){
        return this.temperatureService.saveNewTemperature(newTemperature);
    }


    @PutMapping("/temperatures/{id}")
    public Temperature putTemperature(@RequestBody Temperature newTemperature, @PathVariable long id){
        return this.temperatureService.replaceTemperature(newTemperature, id);
    }

    @DeleteMapping("/temperatures/{id}")
    public void deleteTemperature(@PathVariable long id){
        this.temperatureService.deleteTemperature(id);
    }
}
