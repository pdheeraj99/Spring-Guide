package io.mawa.spring.core.inheritance;

public class ChildBean {
    private String brand;
    private String engine;
    private boolean spoiler;
    private boolean leatherSeats;

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

    public boolean hasSpoiler() {
        return spoiler;
    }

    public void setSpoiler(boolean spoiler) {
        this.spoiler = spoiler;
    }

    public boolean hasLeatherSeats() {
        return leatherSeats;
    }

    public void setLeatherSeats(boolean leatherSeats) {
        this.leatherSeats = leatherSeats;
    }

    @Override
    public String toString() {
        return "ChildBean{" +
                "brand='" + brand + '\'' +
                ", engine='" + engine + '\'' +
                ", spoiler=" + spoiler +
                ", leatherSeats=" + leatherSeats +
                '}';
    }
}
