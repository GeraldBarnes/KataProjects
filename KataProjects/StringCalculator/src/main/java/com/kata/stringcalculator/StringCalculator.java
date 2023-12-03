/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.kata.stringcalculator;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *
 * @author geral
 */
public class StringCalculator {

    public static void main(String[] args) {
        while(true){
            StringCalculator stringCalculator = new StringCalculator();
            stringCalculator.scanner();
        }
    }
    
    public void bufferedReaderInput(){
        System.out.println("Please enter numbers: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int product =0;
        StringCalculator stringCalculator = new StringCalculator();
        try {
            product = stringCalculator.Add3(br.readLine());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(product);
    }
    public void scanner(){
        System.out.println("Please enter numbers, enter an empty line to calculate: ");
        Scanner s = new Scanner(System.in);
        int product;
        String input = "";
        while (s.hasNextLine()){ 
            String read = s.nextLine();
            if(read == null || read.isEmpty()){
                break; 
            }
            input = input.isEmpty()? read:input + "\n" + read;
            
        }
        StringCalculator stringCalculator = new StringCalculator();
//        product = stringCalculator.Add1(input);
//        System.out.println("Total of Add1 is: " + product);
//        
//        product = stringCalculator.Add2(input);
//        System.out.println("Total of Add2 is: " + product);
//        
//        product = stringCalculator.Add3(input);
//        System.out.println("Total of Add3 is: " + product);
//        
//        product = stringCalculator.Add4(input);
//        System.out.println("Total of Add4 is: " + product);
        
        try{
//            product = stringCalculator.Add5(input);
//            System.out.println("Total of Add5 is: " + product);
//            
//            product = stringCalculator.Add6(input);
//            System.out.println("Total of Add6 is: " + product);
            
//            product = stringCalculator.Add7(input);
//            System.out.println("Total of Add7 is: " + product);

            product = stringCalculator.Add8(input);
            System.out.println("Total of Add8 is: " + product);
        }catch(NegativeNumberException e){
            
        }
    }
    /*
    * Kata step 1
    */
    private int Add1(String number){
        if(number.isBlank() ){
              return 0;  
        }
        int total = 0;
        String[] numbers = number.split(",");
        if(numbers.length > 2){
            System.out.println("Please enter only 1 or 2 numbers!");
            return 0;
        }
        for(String digit : numbers){
            total = total + Integer.parseInt(digit);
        }
        return total;
    }
    /*
    * Kata step 2, unknown number of numbers
    */
    private int Add2(String number){
        if(number.isBlank() ){
              return 0;  
        }
        String[] numbers = number.split(",");
        int total = 0;
        for(String digit : numbers){
            total = total + Integer.parseInt(digit);
        }
        return total;
    }
    
    /*
    * Kata step 3, should be able to handle new lines
    */
    private int Add3(String number){
        if(number.isBlank() ){
              return 0;  
        }
        number=number.replace("\n", ",");
        String[] numbers = number.split(",");
        int total = 0;
        for(String digit : numbers){
            if(!digit.isBlank())
                total = total + Integer.parseInt(digit);
        }
        return total;
    }
    
    /*
    * Kata step 4, add custom delimitor
    */
    private int Add4(String number){
        if(number.isBlank() ){
              return 0;  
        }
        String delimitor = getDelimitor(number);
        number=number.replace("\n", delimitor);
        String[] numbers = number.split(delimitor);
        int total = 0;
        for(String digit : numbers){
            digit = digit.replaceFirst("^[//].", "");
            if(!digit.isBlank())
                total = total + Integer.parseInt(digit);
        }
        return total;
    }
    private String getDelimitor(String userInput){
        if(userInput.matches("(?s)^//.[\\n].*")){
            System.out.println("Delimitor is: " + userInput.charAt(2));
            return "" + userInput.charAt(2);
        }
        System.out.println("Delimitor is: " + ",");
        return ",";
    }
    
    /*
    * Kata step 5, negatives not allowed
    */
    private int Add5(String number) throws NegativeNumberException {
        if(number.isBlank() ){
              return 0;  
        }
        String delimitor = getDelimitor(number);
        number=number.replace("\n", delimitor);
        String[] numbers = number.split(delimitor);
        int total = 0;
        String negativeDigits = "Negatives not allowed: ";
        for(String digit : numbers){
            digit = digit.replaceFirst("^[//].", "");
            if(digit.startsWith("-")){
                negativeDigits = negativeDigits + digit;
            }
            if(!digit.isBlank())
                total = total + Integer.parseInt(digit);
        }
        if(!negativeDigits.equals("Negatives not allowed: ")){
            System.out.println(negativeDigits);
            throw new NegativeNumberException();
        }
        return total;
    }
    
    /*
    * Kata step 6, numbers > 1000 should be ignored
    */
    private int Add6(String number) throws NegativeNumberException {
        if(number.isBlank() ){
              return 0;  
        }
        String delimitor = getDelimitor(number);
        number=number.replace("\n", delimitor);
        String[] numbers = number.split(delimitor);
        int total = 0;
        String negativeDigits = "Negatives not allowed: ";
        for(String digit : numbers){
            digit = digit.replaceFirst("^[//].", "");
            if(digit.startsWith("-")){
                negativeDigits = negativeDigits + digit;
            }
            if(!digit.isBlank()){
                int inputDigit = Integer.parseInt(digit);
                if(inputDigit < 1001)
                total = total + inputDigit;
            }
        }
        if(!negativeDigits.equals("Negatives not allowed: ")){
            System.out.println(negativeDigits);
            throw new NegativeNumberException();
        }
        return total;
    }

    /*
    * Kata step 7, Delimitor any length
    */
    private int Add7(String number) throws NegativeNumberException {
        if(number.isBlank() ){
              return 0;  
        }
        String delimitor = getDelimitorAnyLength(number);
        number=number.replace("\n", delimitor);
        String[] numbers = number.split(delimitor);
        int total = 0;
        String negativeDigits = "Negatives not allowed: ";
        for(String digit : numbers){
            digit = digit.replaceFirst("^//\\[", "");
            digit = digit.replaceFirst("\\]", "");
            if(digit.startsWith("-")){
                negativeDigits = negativeDigits + digit;
            }
            if(!digit.isBlank()){
                int inputDigit = Integer.parseInt(digit);
                if(inputDigit < 1001)
                total = total + inputDigit;
            }
        }
        if(!negativeDigits.equals("Negatives not allowed: ")){
            System.out.println(negativeDigits);
            throw new NegativeNumberException();
        }
        return total;
    }
    
    /*
    * Kata step 8 & 9, allow multiple delimitor any length
    */
    private int Add8(String number) throws NegativeNumberException {
        if(number.isBlank() ){
              return 0;  
        }
        String delimitors = getAllowMultipleDelimitor(number);
        number=number.replace("\n", ""+delimitors.charAt(1));
        String[] numbers = number.split(delimitors);
        int total = 0;
        String negativeDigits = "Negatives not allowed: ";
        for(String digit : numbers){
            digit = digit.replaceFirst("^//\\[", "");
            digit = digit.replaceFirst("\\]", "");
            digit = digit.replaceFirst("\\[", "");
            if(digit.startsWith("-")){
                negativeDigits = negativeDigits + digit;
            }
            if(!digit.isBlank()){
                int inputDigit = Integer.parseInt(digit);
                if(inputDigit < 1001)
                total = total + inputDigit;
            }
        }
        if(!negativeDigits.equals("Negatives not allowed: ")){
            System.out.println(negativeDigits);
            throw new NegativeNumberException();
        }
        return total;
    }
    
    private String getAllowMultipleDelimitor(String userInput){
        List<String> delimitors = new ArrayList<String>();
        if(userInput.matches("^//\\[.+\\]\\[.+\\][\\n][\\s\\S]*")){
            String delimiter = userInput.replaceAll("^//\\[(.+)\\]\\[.+\\][\\n][\\s\\S]*", "$1");
            String delimiter2 = userInput.replaceAll("^//\\[.+\\]\\[(.+)\\][\\n][\\s\\S]*", "$1");
            System.out.println("Delimitor is: " + delimiter);
            System.out.println("Delimitor 2 is: " + delimiter2);
            
            return "[" + delimiter + delimiter2 + "]";
        }
        if(userInput.matches("^//\\[.+\\][\\n][\\s\\S]*")){
            String delimiter = userInput.replaceAll("^//\\[(.+)\\][\\n][\\s\\S]*", "$1");
            System.out.println("Delimitor is: " + delimiter);

            return delimiter;
        }
        System.out.println("Delimitor is: " + ",");
        return "[,]";
    }
    
    private String getDelimitorAnyLength(String userInput){
        if(userInput.matches("^//\\[.+\\][\\n][\\s\\S]*")){
            String delimiter = userInput.replaceAll("^//\\[(.+)\\][\\n][\\s\\S]*", "$1");
            System.out.println("Delimitor is: " + delimiter);
            return "" + delimiter;
        }
        System.out.println("Delimitor is: " + ",");
        return ",";
    }
    private static class NegativeNumberException extends Exception {

        public NegativeNumberException() {
        }
    }
}
