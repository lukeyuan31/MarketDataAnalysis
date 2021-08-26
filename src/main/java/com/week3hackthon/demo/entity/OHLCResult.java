package com.week3hackthon.demo.entity;

public class OHLCResult {
    private String name;
    private String max;
    private String min;
    private String avg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "OHLCResult{" +
                "name='" + name + '\'' +
                ", max='" + max + '\'' +
                ", min='" + min + '\'' +
                ", avg='" + avg + '\'' +
                '}';
    }
}
