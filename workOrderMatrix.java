import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class workOrderMatrix {
    public static void main( String[] args) throws Exception {
        System.out.println("Work Order Matrix\n\nMake sure you have applied the desired multipliers!\n");
        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("Are you sure you want to continue?YES{y} NO{n}");
            String userIn = myObj.nextLine();
            if(userIn != "y"){
                System.exit(0);
            }
        }
    }
}