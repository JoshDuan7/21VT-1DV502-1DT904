package assignment1;

import java.util.Scanner;

public class Hex2Dec {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a hex number: ");
        
        String input = scan.nextLine();
        String hexnum = input.toUpperCase();
        
        int decimal = hexToDecimal(hexnum);

        System.out.println("The decimal value for " + input + " is " + decimal + ".");
        scan.close();
    }

    // method for transfering hex to decimal
    private static int hexToDecimal (String hex) {

        String hexstring = "0123456789ABCDEF";
        int dec = 0;

        for (int i = 0; i < hex.length(); i++) {
                char ch = hex.charAt(i); // find the character from user input
                int n = hexstring.indexOf(ch); // identify the index of character from input
                int pow_num = hex.length() - (i+1); // calculate the power of each character
                double change_part = Math.pow(16, pow_num); // calculate the 16^? the changing part
                dec += (n * change_part); // use the provided formula
            }
        
        // Method 2:  a simple method also can transfer hex to decimal
        // for (int i = 0; i < hex.length(); i++) {
        //     char ch = hex.charAt(i);
        //     int n = hexstring.indexOf(ch);
        //     dec = dec*16 + n;
        // }

        return dec;
    }
}
