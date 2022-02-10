import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.HashMap;


class duplicateSearch implements Runnable{
    public volatile ArrayList<int[]> mainDataArray = new ArrayList<int[]>();
    public duplicateSearch(ArrayList<int[]> _array){
        this.mainDataArray = _array;
    }
    public void run(){
        System.out.println("Searching for duplicates...");
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] woArray;
        for (int i = 0; i < mainDataArray.size(); i = i + mainDataArray.get(i).length){
            woArray = mainDataArray.get(i);
            if (map.get(woArray[0]) == null){
                map.put(woArray[0], 1);
            } else {
                map.put(woArray[0], map.get(woArray[0]) + 1);
            }
        }
        Set<Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Entry<Integer, Integer> entry : entrySet) {               
            if(entry.getValue() > 1){
                System.out.println("Duplicate Element : "+ entry.getKey() +" - found "+ entry.getValue() +" times.");
            }
        }
        System.out.println("Duplicate search complete.");
    }
}


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


class calcSort{
    public ArrayList<int[]> calculateSort(ArrayList<int[]> inputArray){
        for (int i = 0; i < inputArray.size(); i = i + inputArray.get(i).length){
            int[] wrkodr = inputArray.get(i);
            wrkodr[wrkodr.length - 1] = calcDistance(wrkodr);
            inputArray.set(i, wrkodr);
        }
        Collections.sort(inputArray, new Comparator<int[]>() {
            public int compare(int[] a, int[] b){
                return Integer.valueOf(a[a.length - 1]).compareTo(Integer.valueOf(b[b.length -1]));
            }
        });
        return inputArray;
    }
    public int calcDistance(int[] input){
        int[] testPoint = {900, -14, 0};
        int dem0 = input[4] / input[1];
        int dem1 = input[3];
        int dem2 = input[5];
        int x = (dem0 - testPoint[0]) ^ 2;
        int y = (dem1 - testPoint[1]) ^ 2;
        int z = (dem2 - testPoint[2]) ^ 2;
        int underSQroot = x + y + z;
        int distanceToPoint = (int) Math.sqrt(underSQroot);
        return distanceToPoint;
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
                for (int y = 0; y < csvData.get(y).length * 10; y = y + csvData.get(y).length){
                    System.out.println(Arrays.toString(csvData.get(y)));
                }
                System.out.println("CSV data extracted sucessfully.");
                Thread t1 = new Thread(new duplicateSearch(csvData));
                t1.start();
                calcSort calculateAndSort = new calcSort();
                csvData = calculateAndSort.calculateSort(csvData);
                t1.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}