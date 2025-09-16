package io.mawa.spring.core.inheritance;

public class ParentBean {
    private String brand;
    private String engine;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "brand='" + brand + '\'' +
                ", engine='" + engine + '\'';
    }
}
