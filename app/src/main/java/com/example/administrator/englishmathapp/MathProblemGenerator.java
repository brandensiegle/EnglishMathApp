package com.example.administrator.englishmathapp;

import android.support.v7.app.AppCompatActivity;

import java.util.Random;
/**
 * Created by Administrator on 2017-06-04.
 */

public class MathProblemGenerator extends AppCompatActivity {

    static String[] ones = new String[] { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
    static String[] teens = new String[] { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    static String[] tens = new String[] { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    static String[] thousandsGroups = new String[] { "", " Thousand", " Million", " Billion" };

    public MathProblemGenerator(){

    }

    //-----------------------------
    //Static method getProblem(int)
    //@param: int val-> the maximum answer of the problem to be generated
    //
    //@return: String[]-> the components of a simple problem
    //         [0]: first operand;
    //         [1]: second operand;
    //         [2]: the operation of the problem;
    //         [3]: answer of the problem to be solved
    //-----------------------------
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

    //-----------------------------
    //Helper method FriendlyInteger(int, String, int)
    //@param: int n-> number to be written out
    //@param: String leftDigits-> string of the value on the left written out
    //@param: int thousands-> the group of thousands to be written out
    //
    //@return: String: a written out string of the number given
    //-----------------------------
    private static String FriendlyInteger(int n, String leftDigits, int thousands) {
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

    //-----------------------------
    //Helper method IntegerToWritten(int)
    //@param: int n-> the number to be written out in English
    //
    //@return: String-> a written representation of the integer provided
    //-----------------------------
    private static String IntegerToWritten(int n) {
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
