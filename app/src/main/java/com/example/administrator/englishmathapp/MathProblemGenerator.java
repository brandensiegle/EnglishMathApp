package com.example.administrator.englishmathapp;

import java.util.Random;
/**
 * Created by Administrator on 2017-06-04.
 */

public class MathProblemGenerator {

    static String[] ones = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
    static String[] teens = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    static String[] tens = new String[] { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    static String[] thousandsGroups = new String[] { "", " Thousand", " Million", " Billion" };

    public MathProblemGenerator(){

    }

    public static String[] getProblem(int val){
        int calculatedAnswer = val+1;
        int firstNumber = 0;
        int secondNumber = 0;

        while (calculatedAnswer>val) {
            Random rNumber = new Random();
            firstNumber = rNumber.nextInt(val);
            secondNumber = rNumber.nextInt(val);
            calculatedAnswer = firstNumber + secondNumber;
        }

        String[] problem = new String[4];

        problem[0] = IntegerToWritten(firstNumber);
        problem[1] = IntegerToWritten(secondNumber);
        problem[2] = "+";
        problem[3] = IntegerToWritten(calculatedAnswer);

        return problem;
    }


    private static String FriendlyInteger(int n, String leftDigits, int thousands)
    {
        if (n == 0)
        {
            return leftDigits;
        }

        String friendlyInt = leftDigits;

        if (friendlyInt.length() > 0)
        {
            friendlyInt += " ";
        }

        if (n < 10)
        {
            friendlyInt += ones[n];
        }
        else if (n < 20)
        {
            friendlyInt += teens[n - 10];
        }
        else if (n < 100)
        {
            friendlyInt += FriendlyInteger(n % 10, tens[n / 10 - 2], 0);
        }
        else if (n < 1000)
        {
            friendlyInt += FriendlyInteger(n % 100, (ones[n / 100] + " Hundred"), 0);
        }
        else
        {
            friendlyInt += FriendlyInteger(n % 1000, FriendlyInteger(n / 1000, "", thousands+1), 0);
            if (n % 1000 == 0)
            {
                return friendlyInt;
            }
        }

        return friendlyInt + thousandsGroups[thousands];
    }

    private static String IntegerToWritten(int n)
    {
        if (n == 0)
        {
            return "Zero";
        }
        else if (n < 0)
        {
            return "Negative " + IntegerToWritten(-n);
        }

        return FriendlyInteger(n, "", 0);
    }

}
