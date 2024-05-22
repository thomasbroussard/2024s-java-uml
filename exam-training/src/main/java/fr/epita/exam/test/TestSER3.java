package fr.epita.exam.test;

import fr.epita.exam.datamodel.Passenger;
import fr.epita.exam.services.PassengerCSVDAO;

import java.util.List;

public class TestSER3 {

    public static void test(){
        PassengerCSVDAO dao = new PassengerCSVDAO();
        List<Passenger> passengers = dao.readAll();
        dao.writeAll(passengers);
    }
}
