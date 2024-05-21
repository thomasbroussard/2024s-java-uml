package fr.epita.titanic.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XYChartSerie<T> {

    private String name;
    private List<T> xValues;
    private List<Number> yValues;

    public XYChartSerie(String name, Collection<T> xValues, Collection<Number> yValues) {
        this.name = name;
        this.xValues = new ArrayList<>(xValues);
        this.yValues = new ArrayList<>(yValues);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getxValues() {
        return xValues;
    }

    public void setxValues(List<T> xValues) {
        this.xValues = xValues;
    }

    public List<Number> getyValues() {
        return yValues;
    }

    public void setyValues(List<Number> yValues) {
        this.yValues = yValues;
    }
}
