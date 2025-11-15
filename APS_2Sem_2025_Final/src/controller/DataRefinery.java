package controller;
import java.util.List;

public final class DataRefinery {

    private String[][] totalListState = {
        {"0","Acre"},
        {"0","Amapá"},
        {"0","Amazonas"},
        {"0","Maranhão"},
        {"0","Mato Grosso"},
        {"0","Pará"},
        {"0","Rondônia"},
        {"0","Roraima"},
        {"0","Tocantins"},
    };

    private String[][] totalListYear = new String[37][2];

    private int temp;

    public DataRefinery(){
        temp = 1988;
        for (int i = 0; i < totalListYear.length; i++) {
            totalListYear[i] = new String[2];
            totalListYear[i][0] = "0";
            totalListYear[i][1] = String.valueOf(temp);
            temp++;
        }
    }

    public void getTotalListState() {
        for (String[] state : totalListState){
            System.out.println(state[0]+", "+state[1]);
        }
    }

    public void getTotalListYear() {
        for (String[] year : totalListYear){
            System.out.println(year[0]+", "+year[1]);
        }
    }

    public String[][] totalAreaPerState(List<List<String>> records){
        for (String[] state : totalListState){
            temp = 0;
            for (List<String> rec : records){
                if (rec.size() > 2 && rec.get(2).equals(state[1])) {
                    try {
                        temp += Integer.parseInt(rec.get(1));
                    } catch (NumberFormatException e) {
                    }
                }
            }
            state[0] = String.valueOf(temp);
        }

        return totalListState;
    }

    public String[][] totalAreaPerYear(List<List<String>> records){
        for (String[] year : totalListYear){
            temp = 0;
            for (List<String> rec : records){
                if (rec.size() > 0 && rec.get(0).equals(year[1])) {
                    try {
                        temp += Integer.parseInt(rec.get(1));
                    } catch (NumberFormatException e) {
                    }
                }
            }
            year[0] = String.valueOf(temp);
        }

        return totalListYear;
    }

    public List<List<String>> coreDataInserter(List<List<String>> inputData, List<List<String>> outputData){

        outputData.clear();
        outputData.addAll(inputData);

        return outputData;

    }

}
