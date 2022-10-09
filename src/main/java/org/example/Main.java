package org.example;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Float> initialValue = new HashMap<>();
        initialValue.put("USD", 100F);
        initialValue.put("CNY", 50F);
        initialValue.put("GBP", -30F);
        initialValue.put("EUR", 200F);

        System.out.println("Enter Currency and amount");

        try {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] splitInput = input.split(" ");


            System.out.println("You entered string " + splitInput[0] + " " + splitInput[1]);
            String inputCurrency = splitInput[0];
            float inputAmount = Float.parseFloat(splitInput[1]);

            ThreadCalculate t1 = new ThreadCalculate(inputCurrency, inputAmount, initialValue);
            t1.start();

            Scanner in2 = new Scanner(System.in);
            String input2 = in2.nextLine();
            System.out.println("You entered string " + input2);
            if (input2.equalsIgnoreCase("quit")) {
                t1.stop();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Invalid Input!! Try Again");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong! try again");
        }

    }
}
