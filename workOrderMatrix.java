import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class handleCSV{
    public ArrayList<int[]> extractData(){
        ArrayList<int[]> csvArray = new ArrayList<int[]>();
        String wrkingDir = new File("").getAbsolutePath();
        //System.out.println(wrkingDir);
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(wrkingDir + "\\test_wo.csv"));
            String row;
            while ((row = csvReader.readLine()) != null){
                String[] data0 = row.split(",");
                if (data0[2].equals("A")){
                    data0[2] = "60";
                } else if (data0[2].equals("B")){
                    data0[2] = "50";
                } else if (data0[2].equals("C")){
                    data0[2] = "40";
                } else if (data0[2].equals("D")){
                    data0[2] = "30";
                } else if (data0[2].equals("E")){
                    data0[2] = "20";
                } else if (data0[2].equals("F")){
                    data0[2] = "10";
                } else {
                    data0[2] = "100";
                }
                //System.out.println(Arrays.toString(data0));
                int[] data1;
                data1 = new int[data0.length];
                for (int i = 0; i < data0.length; i++){
                    try{
                        data1[i] = Integer.parseInt(data0[i]);
                        csvArray.add(data1);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvArray.remove(0);
        return csvArray;
    }
}

public class workOrderMatrix {
    public static void main( String[] args) throws Exception {
        System.out.println("Work Order Matrix\n\nMake sure you have applied the desired multipliers!\n");
        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("Are you sure you want to continue?YES{y} NO{n}");
            String userIn = myObj.nextLine();
            //System.out.println(userIn.getClass());
            if(userIn == "y"){
                System.out.println("Exiting...");
                System.exit(0);
            } else {
                System.out.println("\nExtracting Data from CSV...");
                handleCSV readCSV = new handleCSV();
                ArrayList<int[]> csvData = new ArrayList<int[]>();
                csvData = readCSV.extractData();
                for (int y = 0; y < csvData.size(); y = y + csvData.get(y).length){
                    System.out.println(Arrays.toString(csvData.get(y)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}