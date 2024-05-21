package fr.epita.titanic.services;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;

public class BarChartBuilder {


    List<XYChartSerie> series = new ArrayList<>();

    String title;

    String xTitle;
    String yTitle;


    public BarChartBuilder(String title){
        this.title = title;
    }

    public BarChartBuilder xTitle(String title){
        xTitle = title;
        return this;

    }
    public BarChartBuilder yTitle(String title){
        yTitle = title;
        return this;

    }

    public BarChartBuilder serie(XYChartSerie serie){
        this.series.add(serie);
        return this;
    }


    public void buildAndDisplay() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .xAxisTitle(xTitle)
                .yAxisTitle(yTitle)
                .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);

        for (XYChartSerie serie: series){
            chart.addSeries(serie.getName(),
                    serie.getxValues(), serie.getyValues());
        }
        // Series


        new SwingWrapper(chart).displayChart();
    }
}
