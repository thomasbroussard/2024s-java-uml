package fr.epita.titanic.launcher;

import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Function;
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
        Function<Passenger, String> groupingFunction = Passenger::getSex;
        analyse(passengers, Passenger::getSex);
        analyse(passengers, Passenger::getpClass);
        analyse(passengers, p -> String.valueOf(p.isSurvived()));





    }

    private static void analyse(List<Passenger> passengers, Function<Passenger, String> groupingFunction) {
        Map<String, List<Passenger>> collect = passengers.stream()
                .collect(Collectors.groupingBy(groupingFunction));

        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        collect.forEach((k,v) -> {
            System.out.println(k + ":" + v.size());
            keys.add(k);
            values.add(v.size());
        });

        displayDistributionChart("Distribution over gender (Sex variable)", keys, values);
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
