package fr.epita.titanic.launcher;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.Arrays;

public class ExampleBarChart {

    public static void main(String[] args) {
        new SwingWrapper(getChart()).displayChart();
    }


    public static CategoryChart getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Score").yAxisTitle("Number").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);

        // Series
        chart.addSeries("test 1", Arrays.asList(new Integer[]{0, 1, 2, 3, 4}), Arrays.asList(new Integer[]{4, 5, 9, 6, 5}));

        return chart;
    }

}
