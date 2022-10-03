import java.security.Principal;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args) {

        int principal = (int)readNumber("Principal: ", 1000, 1000000);
        float annualInterest = (float)readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte)readNumber("Period (years): ", 1, 30);

        printMortgage(principal, annualInterest, years);
        PrintPaymentSchedule(principal, annualInterest, years);
    }

    public static void printMortgage(int principal, float annualInterest, byte years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("________");
        System.out.println("Monthly Payment: " + mortgageFormatted);
    }

    public static void PrintPaymentSchedule(int principal, float annualInterest, byte years) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("________________");
        for(short month = 1; month < years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner input = new Scanner(System.in);
        double value;
        while(true){
            System.out.print(prompt);
            value = input.nextDouble();
            if(value >= min && value <= max)
            break;
            System.out.println("Enter a value between" + min + "and" + max);
        }
        return value;
    }
    public static double calculateBalance(int principal,
                                          float annualInterest,
                                          byte years,
                                          short numberOfPaymentMade){
        float monthlyInterest = annualInterest/PERCENT/MONTHS_IN_YEAR;
        int numberOfPayment = years * MONTHS_IN_YEAR;

        double balance = principal
                        * (Math.pow(1 + monthlyInterest, numberOfPayment)- Math.pow(1 + monthlyInterest, numberOfPaymentMade))
                        / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);
        return balance;
    }
    public static double calculateMortgage(int principal,
                                  float annualInterest,
                                  byte years){
        float monthlyInterest = annualInterest/PERCENT/MONTHS_IN_YEAR;
        int numberOfPayment = years*MONTHS_IN_YEAR;

        double mortgage = principal
                          * monthlyInterest * (Math.pow(1 + monthlyInterest, numberOfPayment))
                          / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);
        return mortgage;
    }

}













