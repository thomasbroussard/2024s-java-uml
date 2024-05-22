package fr.epita.exam.services;

import fr.epita.exam.datamodel.Passenger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PassengerCSVDAO {

    public List<Passenger> readAll() {
        List<Passenger> results = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of("./exam-training/data.csv"));
            lines.remove(0);

            //Allen, Miss Elisabeth Walton ;1st ;29 ;female;1
            for (String line : lines) {
                try {
                    String[] lineParts = line.split(";");
                    String name = lineParts[0];
                    String pclass = lineParts[1];
                    Double age = Double.parseDouble(lineParts[2]);
                    String sex = lineParts[3];
                    Boolean survived = "1".equals(lineParts[4]);
                    Passenger p = new Passenger(name, pclass, age, sex, survived);
                    results.add(p);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("problem with line : " + line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
