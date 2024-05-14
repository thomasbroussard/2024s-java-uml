package fr.epita.titanic.launcher;

import fr.epita.titanic.datamodel.Passenger;

public class Main {

    public static void main(String[] args) {
        String example = " Allen, Miss Elisabeth Walton; 1 st;29;female;1";

        String[] split = example.split(";");
        String name = split[0];




        Passenger instance = new Passenger();
    }
}
