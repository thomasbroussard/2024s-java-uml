package fr.epita.titanic.launcher;

import fr.epita.titanic.datamodel.Passenger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("titanic/data.csv"));
        lines.remove(0); // remove the headers
        List<Passenger> passengers = new ArrayList<>();
        for (String line: lines) {
            Passenger passenger = getPassengerFromCSV(line);
            if (passenger != null) {
                passengers.add(passenger);
            }
        }
     //   List<Passenger> passengerList = lines.parallelStream().map(Main::getPassengerFromCSV).collect(Collectors.toList());
        System.out.println(passengers.size());
        OptionalDouble average = passengers.stream().mapToDouble(Passenger::getAge).average();
        System.out.println(average.getAsDouble());
        Map<String, List<Passenger>> collect = passengers.stream()
                .collect(Collectors.groupingBy(Passenger::getSex));

        collect.forEach((k,v) -> {
            System.out.println(k + ":" + v.size());
        });


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
