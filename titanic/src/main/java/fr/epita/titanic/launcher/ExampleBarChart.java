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
        chart.addSeries("Distribution over Gender",
                Arrays.asList(new String[]{"male", "female"}),
                Arrays.asList(new Integer[]{468, 288}));

        return chart;
    }

}
