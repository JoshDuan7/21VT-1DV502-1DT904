package assignment1;

import java.util.Scanner;

public class Diamonds {
    
    private static void printDiamonds (int num) {
        for (int i = 1; i <= num; i++) {
            for (int j = num; j>i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 1; i <= num - 1; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }
            for (int j = 2 * num; j > 2 * i + 1; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
        // create bottom space
        System.out.println();
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // print diamonds
        while (true) {
            System.out.print("Give a positive number: ");
            int num = scan.nextInt();
            if (num < 0) {
                System.out.println("Invalid value！Exit!");
                break;
            }
            printDiamonds(num);
        
        }
        scan.close();
    }
}
