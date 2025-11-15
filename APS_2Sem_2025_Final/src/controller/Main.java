package controller;
import java.util.ArrayList;
import java.util.List;
import view.MainInterface;

public class Main {

    public static List<List<String>> finalData;
    public static String[][] totalAreaPerState;
    public static String[][] totalAreaPerYear;

    public static void main(String[] args) {

        CSVReader reader = new CSVReader();
        DataRefinery dataRefinery = new DataRefinery();

        finalData = new ArrayList<>();
        List<List<String>> rawData = reader.bufferedReader("src/model/terrabrasilis_legal_amazon_15_10_2025_1760542506765.csv");

        dataRefinery.coreDataInserter(rawData, finalData);

        totalAreaPerState = dataRefinery.totalAreaPerState(finalData);
        totalAreaPerYear = dataRefinery.totalAreaPerYear(finalData);


        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainInterface(finalData, totalAreaPerState, totalAreaPerYear);
        });
    }
}
