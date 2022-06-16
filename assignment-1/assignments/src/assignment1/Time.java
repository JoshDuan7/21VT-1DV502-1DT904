package assignment1;

import java.util.Scanner;

public class Time {

    public static void main(String[] args) {
        int seconds = readSeconds();
        printTime(seconds);
    }

    // receive result
    private static int readSeconds () {
		Scanner scan = new Scanner(System.in);
        System.out.print("Give a number of seconds: "); // receive result
        int seconds = scan.nextInt();
        scan.close();
        return seconds;
	}
    
    // calculate the results
	private static void printTime (int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remain_sec = seconds - hours * 3600 - minutes * 60;
        System.out.println("This corresponds to: " + hours + " hours, " + minutes+" minutes and " + remain_sec + " seconds.");
	}
	 
}
