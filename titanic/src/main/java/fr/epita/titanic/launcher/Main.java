package fr.epita.titanic.launcher;

import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.BarChartBuilder;
import fr.epita.titanic.services.XYChartSerie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static fr.epita.titanic.services.Utils.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "titanic/data.csv";
        List<Passenger> passengers = loadPassengers(path);
        System.out.println(passengers.size());

        OptionalDouble average = passengers.stream().mapToDouble(Passenger::getAge).average();
        System.out.println(average.getAsDouble());

        //call the visualization logic
       // analyzeDistribution(passengers, Passenger::getSex, Passenger::isSurvived);
        //analyzeDistribution(passengers, Passenger::getpClass,Passenger::isSurvived);
        Function<Passenger, String> splitFunction = p -> String.valueOf(p.isSurvived());
        analyzeDistributionChatGPT(passengers, "Pclass", Passenger::getpClass, splitFunction);
        analyzeDistributionChatGPT(passengers, "sex", Passenger::getSex, splitFunction);
    }

    public static void analyzeDistributionChatGPT(List<Passenger> passengers, String attributeName,
                                           Function<Passenger, String> groupingFunction,
                                           Function<Passenger, String> splitFunction) {
        // Split passengers based on the splitFunction
        Map<String, List<Passenger>> splitData = passengers.stream()
                .collect(Collectors.groupingBy(splitFunction));

        // Prepare series data for each split subset
        List<XYChartSerie> seriesList = new ArrayList<>();
        splitData.forEach((splitKey, passengerList) -> {
            Map<String, Long> groupByCount = groupByCount(passengerList, groupingFunction);
            seriesList.add(new XYChartSerie(splitKey, groupByCount.keySet(), groupByCount.values()));
        });


        // Display bar chart
        BarChartBuilder barChartBuilder = new BarChartBuilder("Distribution over " + attributeName)
                .yTitle("count")
                .xTitle("Pclass");
        seriesList.forEach(barChartBuilder::serie);
        barChartBuilder.buildAndDisplay();
    }

    private static Map<String, Long> groupByCount(List<Passenger> passengers, Function<Passenger, String> groupingFunction) {
        return passengers.stream()
                .collect(Collectors.groupingBy(groupingFunction, Collectors.counting()));
    }

    private static List<Passenger> loadPassengers(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        lines.remove(0); // remove the headers
        List<Passenger> passengers = new ArrayList<>();
        for (String line: lines) {
            Passenger passenger = getPassengerFromCSV(line);
            if (passenger != null) {
                passengers.add(passenger);
            }
        }
        return passengers;
    }


    private static Passenger getPassengerFromCSV(String example) {
        try {
            String[] split = example.split(";");
            String name = split[0].trim();
            String pclass = split[1].trim();
            Double age = Double.parseDouble(split[2].trim());
            String sex = split[3].trim();
            boolean survived = "1".equals(split[4].trim());

            Passenger instance = new Passenger();
            instance.setName(name);
            instance.setAge(age);
            instance.setpClass(pclass);
            instance.setSex(sex);
            instance.setSurvived(survived);
            return instance;
        } catch (Exception e){
            System.out.println("error at line: " + example + " " + e.getMessage());
            return null;
        }
    }
}
