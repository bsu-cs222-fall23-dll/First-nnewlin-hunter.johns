package edu.bsu.cs222;

public class Main {
    public String input(String input) {
        return input;
    }


    public Object inputValidator(String input) {
        if (input.isEmpty()){
            System.err.print("Invalid input");
            return null;
        }
        else{
            return input(input);
        }
    }
}
