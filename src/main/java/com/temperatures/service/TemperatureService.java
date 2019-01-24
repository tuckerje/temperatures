package com.temperatures.service;

import com.temperatures.model.Temperature;
import com.temperatures.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TemperatureService {

    private final TemperatureRepository temperatureRepository;

    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    /**
     *  Fulfills get get service and duplicates records with Fahrenheit temperature
     * @return list of {@link Temperature}
     */

    public List<Temperature> getTemperatures() {
        List<Temperature> tempsCelsius = temperatureRepository.findAll();

        // Create copy of each converted record
        List<Temperature> tempsFahrenheit = new LinkedList<>();
        for (Temperature temp : tempsCelsius) {
            Temperature copiedTemp = new Temperature();
            copiedTemp.setId(temp.getId());
            copiedTemp.setCreateDate(temp.getCreateDate());
            copiedTemp.setUpdateDate(temp.getUpdateDate());
            double tempFahrenheit = covertCelsiusToFahrenheit(temp.getTemperatureInCelsius());
            copiedTemp.setTemperatureInFahrenheit(tempFahrenheit);
            tempsFahrenheit.add(copiedTemp);
        }

        tempsCelsius.addAll(tempsFahrenheit);
        return tempsCelsius;
    }

    /**
     *  Coverts temperature
     * @param tempCelsius temperature in celsius from database
     * @return temperature in fahrenheit
     */

    private double covertCelsiusToFahrenheit( double tempCelsius){
        return (tempCelsius * (9/ 5)) + 32;
    }

    /**
     * delegate method saves new temperature to the database
     * @param newTemperature new temperature to post in celsius
     * @return saved record
     */

    public Temperature saveNewTemperature(Temperature newTemperature) {
        return temperatureRepository.save(newTemperature);
    }

    /**
     * delegate method deletes records with given id in the database
     * @param id new temperature to post in celsius
     */

    public void deleteTemperature(long id) {
        temperatureRepository.deleteById(id);
    }

    /**
     * Updates the temperature in celsius of temperature record in the database
     * @param newTemperature new temperature in celsius
     * @param id id associated with record
     * @return new record details
     */

    public Temperature replaceTemperature(Temperature newTemperature, long id) {
        return temperatureRepository.findById(id)
                .map(temperature -> {
                    temperature.setTemperatureInCelsius(newTemperature.getTemperatureInCelsius());
                    temperature.setUpdateDate(newTemperature.getUpdateDate());
                    return temperatureRepository.save(temperature);
                })
                .orElseGet(() -> {
                    newTemperature.setId(id);
                    return temperatureRepository.save(newTemperature);
                });
    }
}
