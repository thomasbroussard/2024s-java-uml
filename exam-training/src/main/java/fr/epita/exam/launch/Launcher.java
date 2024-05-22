package fr.epita.exam.launch;

import fr.epita.exam.test.TestDMO2;
import fr.epita.exam.test.TestSER1;
import fr.epita.exam.test.TestSER2;

public class Launcher {

    public static void main(String[] args) {
        TestDMO2.test();
        TestSER1.test();
        TestSER2.test();

    }
}
