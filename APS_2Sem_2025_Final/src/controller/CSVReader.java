package controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CSVReader {

    public List<List<String>> records = new ArrayList<>();

    public List<List<String>> bufferedReader(String filePath){

        records.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }

                if (values.length > 1) {
                    values[1] = values[1].replaceAll(",00$", "");
                    values[1] = values[1].replaceAll("[,.]", "");
                }

                records.add(Arrays.asList(values));
            }
        }
        catch(Exception e) {
            System.out.println("ERROR: !bufferedReader CSVReader.java! -> " + e.getMessage());
        }

        return records;

    }

}
