package fr.epita.exam.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestSER1 {

    public static void test(){
        try {
            List<String> lines = Files.readAllLines(Path.of("./exam-training/data.csv"));
            System.out.println(lines.get(1));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
