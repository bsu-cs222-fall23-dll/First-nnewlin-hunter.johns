package edu.bsu.cs222;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the page you wish to view");

        String input = scanner.nextLine();
        Object validationResult = inputValidator(input);
    }

    public static String input(String input) {
        return input;
    }


    public static Object inputValidator(String input) {
        if (input.isEmpty()){
            System.err.print("Page does not exist");
            return null;
        }
        else{
            return input(input);
        }
    }

    public static Object pass(Object passedValue) {
        if (passedValue != null){
            String validInput = passedValue.toString();
            return validInput;
        }
        else{
            return null;
        }
    }
}
