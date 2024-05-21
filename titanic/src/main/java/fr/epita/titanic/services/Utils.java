package fr.epita.titanic.services;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {


    public static void displayDistributionChart(String name, Collection<String> keys, Collection<Long> values) {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Distribution BarChart")
                .xAxisTitle("Gender")
                .yAxisTitle("count")
                .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);

        // Series
        chart.addSeries(name, new ArrayList<>(keys), new ArrayList<>(values));

        new SwingWrapper(chart).displayChart();

    }
}
