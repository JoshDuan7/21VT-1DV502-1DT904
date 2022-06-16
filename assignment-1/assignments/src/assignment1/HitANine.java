package assignment1;

import java.util.Random;
import java.util.Scanner;

public class HitANine {
    public static void main(String[] args) {
        System.out.println("Playing a game");
        System.out.println("=================");

        Scanner scan = new Scanner(System.in);
        System.out.print("Ready to play? (Y/N) ");
        char selection = scan.next().toUpperCase().charAt(0);

        if (selection == 'Y') {
            startGame(scan);
        } else {
            System.out.println("Game over!"); // If user selects "N" in the beginning
        }
        scan.close();
    }

    private static void startGame(Scanner scan) {
        Random r = new Random();
        int dice1 = r.nextInt(6) + 1;
        System.out.println("You rolled " + dice1);
        System.out.print("Would you like to roll again? (Y/N) ");
        char selection = scan.next().toUpperCase().charAt(0);
        int result;
        int comp_result;
        if (selection == 'Y') {
            int dice2 = r.nextInt(6) + 1;
            result = dice1 + dice2;
            System.out.println("You rolled " + dice2 + " and in total you have " + result);
        } else {
            result = dice1;
        }
        
        // The computer starts
        int comp_dice1 = r.nextInt(6) + 1;
        System.out.println("The computer rolled " + comp_dice1);
        if (comp_dice1 <= 4) {
            int comp_dice2 = r.nextInt(6) + 1;
            comp_result = comp_dice1 + comp_dice2;
            System.out.println("The computer rolls again and gets " + comp_dice2 + " in total " + comp_result);
        } else {
            comp_result = comp_dice1;
        }
        
        // Starting to judge who is the winner
        if (result == 9 && comp_result != 9) {// If user got 9
            System.out.println("You won!");
        } else if (comp_result == 9 && result != 9) { // If computer got 9
            System.out.println("Computer won!");
        } else if (result < 9 && comp_result < 9) {// If both user and computer do not win directly and less than 9
            if (result > comp_result) { // If user is larger than computer
                System.out.println("You won!");
            } else if (result == comp_result) { // If user equals to computer
                System.out.println("It's a draw!");
            } else { // If computer is larger than user
                System.out.println("Computer won!");
            }
        } else if (result > 9 && comp_result < 9) { // User is over large
            System.out.println("You--fat");
            System.out.println("Computer won!");
        } else if (result < 9 && comp_result > 9) { // Computer is over large
            System.out.println("Computer--fat");
            System.out.println("You Won!");
        } else {
            System.out.println("You and computer both get 9! It's a draw!");
        }
    }
}
