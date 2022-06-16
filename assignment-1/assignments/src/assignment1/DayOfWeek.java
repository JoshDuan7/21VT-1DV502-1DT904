package assignment1;

import java.util.Scanner;

public class DayOfWeek {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// check if user wants to play
		System.out.print("Check day of the week ('Y'/'N'): ");
        char selection = scan.next().toUpperCase().charAt(0);
       
		// validate input
        boolean flag;
        flag = validInput(selection);
        
        
        // check result
        while (flag == true) {
        	System.out.print("Enter year: ");
            int year = scan.nextInt();
            
            System.out.print("Enter month (1-12): ");
            int month = scan.nextInt();
            // adjustment for January and February
            if (month == 1) {
                month = 13;
                year -= 1;
            } 
            if (month == 2) {
                month = 14;
                year -= 1;
            }
            
            System.out.print("Enter day of the month (1-31): ");
            int day = scan.nextInt();

            calculation(year, month, day);
            
            // ask user again if continue to check or not
            System.out.print("Check day of the week ('Y'/'N'): ");
            selection = scan.next().toUpperCase().charAt(0);
            flag = validInput(selection);
        }
        scan.close();
    }

    private static boolean validInput (char selection) {
		boolean flag;
        if (selection == 'Y') {
            flag = true;
        } else if (selection == 'N') {
            flag = false;
            System.out.println("Exit query!");
        } else {
            flag = false;
            System.out.println("Invalid input! Exit!");
        }
        return flag;
    }
    
    private static void calculation (int year, int month, int day) {
        String str = "Day of week is ";
		// formula
        int q = day;
        int k = year % 100;
        int j = year / 100;
        int h = ( q + 26 * (month + 1) / 10 + k + k / 4 + j / 4 + 5 * j ) % 7;
        
        // calculate the result
        switch(h) {
            case 0:
                System.out.println(str + "Saturday");
                break;
            case 1:
                System.out.println(str + "Sunday");
                break;
            case 2:
                System.out.println(str + "Monday");
                break;
            case 3:
                System.out.println(str + "Tuesday");
                break;
            case 4:
                System.out.println(str + "Wednesday");
                break;
            case 5:
                System.out.println(str + "Thursday");
                break;
            case 6:
                System.out.println(str + "Friday");
                break;
        }
        System.out.println();
	}


}
