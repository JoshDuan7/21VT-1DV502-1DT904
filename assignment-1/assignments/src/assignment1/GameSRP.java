package assignment1;

import java.util.Random;
import java.util.Scanner;

public class GameSRP {
    public static void main(String[] args) {
        // Initiate all variables
        int countYou = 0;
        int countComputer = 0;
        int countDraw = 0;
        int comp;

        // Initialte Random class, Scanner class
        Random rnd = new Random();
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("Scissor (1), Rock (2), Paper (3) or 0 to quit: ");
            int option = scan.nextInt();
            
            // Validate input
            if ( option < 0 || option > 3 )  {
                System.out.println("Invalid input, Exit!");
                System.out.println("Score: " + countYou + " (you) " + countComputer + " (computer) " + countDraw + " (draw). ");
                break;
            }

            // Game start
            comp = rnd.nextInt(3) + 1;
            if (option == comp) {
                System.out.println("It's a draw!");
                countDraw++;
            } else if (( option == 1 && comp == 3) || (option == 2 && comp == 1) || (option == 3 && comp == 2)) {
                System.out.println("You won, computer had " + getOptionString(comp) + "!");
                countYou++;
            } else if (option == 0) {
                System.out.println("Score: " + countYou + " (you) " + countComputer + " (computer) " + countDraw + " (draw). ");
                break;
            } else {
                System.out.println("You lost, computer had " + getOptionString(comp) + "!");
                countComputer++;
            }
        }  
        scan.close();
    }
    
    // To get different option's string
    private static String getOptionString (int option) {
        switch (option) {
            case 1:
                return "scissor";
            case 2:
                return "rock";
            case 3:
                return "paper";
            default:
                return "";
        } 
    }
}
