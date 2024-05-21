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
        analyzeDistribution(passengers, Passenger::getSex, Passenger::isSurvived);
        analyzeDistribution(passengers, Passenger::getpClass,Passenger::isSurvived);

    }

    private static void analyzeDistribution(List<Passenger> passengers,
                                            Function<Passenger, String> groupingFunction,
                                            Predicate<Passenger> filterFunction) {
        List<Passenger> serie1Data = passengers.stream().filter(filterFunction).collect(Collectors.toList());
        List<Passenger> serie2Data = passengers.stream().filter(p -> !filterFunction.test(p)).collect(Collectors.toList());

        Map<String, Long> groupByCountSerie1 = groupByCount(serie1Data, groupingFunction);
        Map<String, Long> groupByCountSerie2 = groupByCount(serie2Data, groupingFunction);


        displayDistributionChart("Distribution over ", groupByCountSerie1.keySet(), groupByCountSerie1.values());
        List<String> classes = Arrays.asList("1st", "2nd", "3rd");
        new BarChartBuilder("Distribution over Pclass")
                .yTitle("count")
                .xTitle("Pclass")
                .serie(new XYChartSerie("survived", groupByCountSerie1.keySet(), groupByCountSerie1.values() ))
                .serie(new XYChartSerie("not survived", groupByCountSerie2.keySet(), groupByCountSerie2.values()))
                .buildAndDisplay();




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
