package fr.epita.titanic.services;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.List;

public class Utils {


    public static void displayDistributionChart(String name, List<String> keys, List<Integer> values) {

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
        chart.addSeries(name,
                keys, values);

        new SwingWrapper(chart).displayChart();

    }
}
