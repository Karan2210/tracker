package io.egen.pojo;

import java.math.BigInteger;
import java.util.Date;


public class VehicleResult {
    private String vin;
    private String make;
    private String model;
    private int year;
    private int redlineRpm;
    private float maxFuelVolume;
    private Date lastServiceDate;
    private BigInteger highAlertCount;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRedlineRpm() {
        return redlineRpm;
    }

    public void setRedlineRpm(int redlineRpm) {
        this.redlineRpm = redlineRpm;
    }

    public float getMaxFuelVolume() {
        return maxFuelVolume;
    }

    public void setMaxFuelVolume(float maxFuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public BigInteger getHighAlertCount() {
        return highAlertCount;
    }

    public void setHighAlertCount(BigInteger highAlertCount) {
        this.highAlertCount = highAlertCount;
    }

    @Override
    public String toString() {
        return "VehicleResult{" +
                "vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", redlineRpm=" + redlineRpm +
                ", maxFuelVolume=" + maxFuelVolume +
                ", lastServiceDate=" + lastServiceDate +
                ", highAlertCount=" + highAlertCount +
                '}';
    }
}
