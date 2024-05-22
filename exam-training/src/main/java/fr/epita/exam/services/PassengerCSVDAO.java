package fr.epita.exam.services;

import fr.epita.exam.datamodel.Passenger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    public void writeAll(List<Passenger> passengers){
        List<String> lines = new ArrayList<>();
        //PClass ;Name ;Sex ;Age ;Survived
        lines.add("PClass ;Name ;Sex ;Age ;Survived");
        for (Passenger p: passengers) {
            String line = p.getpClass() + ";";
            line += p.getName() + ";";
            line += p.getSex() + ";";
            line += p.getAge() + ";";
            line += p.getSurvived() ? "1":"0" + ";";
            lines.add(line);
        }
        String finalResult = "";
        for(String line: lines){
            finalResult += line  + "\n";
        }
        try {
            Files.writeString(Path.of("data_output.csv"), finalResult, StandardOpenOption.CREATE_NEW);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
