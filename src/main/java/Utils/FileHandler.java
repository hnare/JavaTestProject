package Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {


    public static List<String> readInputFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static Map<String, String[]> readOutputFileAsArray(String filePath) throws IOException {
        Map<String, String[]> outputData = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Split by comma
                if (parts.length >= 4) {
                    String registration = parts[0].replaceAll("\\s+", ""); // Normalize registration key
                    String make = parts[1];
                    String model = parts[2];
                    String year = parts[3];
                    outputData.put(registration, new String[]{make, model, year});
                }
            }
        }
        return outputData;
    }
}



