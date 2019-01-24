package com.temperatures.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // An integer unique identifier

    @Column(name = "temperature")
    @JsonProperty("temperature_celsius")
    private double temperatureInCelsius; // The temperature entered in Celsius

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("temperature_fahrenheit")
    private double temperatureInFahrenheit;

    @CreationTimestamp
    private LocalDateTime createDate; // The date the temperature was recorded

    @UpdateTimestamp
    private LocalDateTime updateDate; // The date the temperature was updated

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperatureInCelsius() {
        return temperatureInCelsius;
    }

    public void setTemperatureInCelsius(double temperatureInCelsius) {
        this.temperatureInCelsius = temperatureInCelsius;
    }

    public double getTemperatureInFahrenheit() {
        return temperatureInFahrenheit;
    }

    public void setTemperatureInFahrenheit(double temperatureInFahrenheit) {
        this.temperatureInFahrenheit = temperatureInFahrenheit;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return id == that.id &&
                Double.compare(that.temperatureInCelsius, temperatureInCelsius) == 0 &&
                Double.compare(that.temperatureInFahrenheit, temperatureInFahrenheit) == 0 &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, temperatureInCelsius, temperatureInFahrenheit, createDate, updateDate);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", temperatureInCelsius=" + temperatureInCelsius +
                ", temperatureInFahrenheit=" + temperatureInFahrenheit +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
