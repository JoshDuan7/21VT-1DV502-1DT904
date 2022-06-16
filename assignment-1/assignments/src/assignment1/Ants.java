package assignment1;

import java.util.Random;

public class Ants {
    public static void main(String[] args) {
        System.out.println("Ants");
        System.out.println("=====");
        System.out.println();
        
        int[] results = new int[10];
        for (int i = 1; i <= 10; i++) {
            int result = simulation();
            System.out.println("Number of steps in simulation " + i + ": " + result);
            results [i-1] = result; // critical to minus 1 !!!!
        }
        double avgSteps = calcuAverage(results);
        System.out.println("Average amount of steps: " + avgSteps);
    }

    private static int simulation() {
        // intialize the chess board with 64 'false'
        boolean [][] chessBoard = new boolean [8][8];
        // initialize the ant's position
        Random rnd = new Random();
        int x = rnd.nextInt(8);
        int y = rnd.nextInt(8);
        int countTrue = 0;
        int countStep = 0;
        chessBoard [x][y] = true;
        do {
            int [] changePosition = howToMove();
            x += changePosition[0];
            y += changePosition[1];
            countStep++;
            
            // creating the boundaries of the chess board
            if (x < 0) {
                x = 0;
                countStep--;
            }
            if (x > 7) {
                x = 7;
                countStep--;
            }
            if (y < 0) {
                y = 0;
                countStep--;
            }
            if (y > 7) {
                y = 7;
                countStep--;
            }
            if (chessBoard [x][y] == false){
                chessBoard [x][y] = true;
                countTrue += 1;
            }
        } while (countTrue < 63); //due to the starting point of the ant is 'true', we only need 63 extra 'true'
        return countStep;
    }

    private static double calcuAverage (int[] results) {
        double sum = 0;
        for (int i = 0; i < results.length; i++) {
            sum += results [i];
        }
        return sum / results.length;
    }

    private static int[] howToMove() {
        int [][] move = {
            {0,1},{0,-1},{1,0},{-1,0},
        };
        int rnd = new Random().nextInt(move.length);
        return move[rnd];
    }

}
