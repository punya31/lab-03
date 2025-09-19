package com.example.listycitylab3;

public class City {
    private String cityName;
    private String provinceName;

    public City(String cityName, String provinceName) {
        this.cityName = cityName;
        this.provinceName = provinceName;
    }

    //adding getters
    public String getCityName() {
        return cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    // adding setters
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return cityName + ", " + provinceName;
    }
}
