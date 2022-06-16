package assignment1;

import java.util.Scanner;

public class PrintCalendar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        // for storing input date
        int[] date = new int [2];
        
        // receive input
        do {
            System.out.print("Enter a year after 1800: ");
            date[0] = scan.nextInt();
        } while (date [0] < 1800);

        System.out.print("Enter a month(1-12): ");
        date[1] = scan.nextInt();

        // print the date above calendar main body
        printDate(date);
        
        // print the calendar main body
        int sumDays = findDaysTo1800(date);
        int days = daysOfTheMonth(date);
        printCalendar(date, sumDays, days);
        scan.close();
    }

    /* Jan 1st 1900 is Monday, based on this we need to calculate how many days between...
       Use these days %7 can find out the first day of the focal month is which day in the week
       Then we can calculate the indentation for the first day */
    private static int findDaysTo1800 (int[] date) {
        int sum = 0;
        
        // add leap year or normal year
        for (int i = 1800; i < date[0]; i++){
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0){
                sum += 366;
            } else {
                sum += 365;
            }
        }
        
        // add days of months
        for (int i = 1; i < date[1]; i++) {
            if (i == 2) {
                if (date[0] % 4 == 0 && date[0] % 100 != 0 || date[0] % 400 == 0) {
                    sum += 29;
                } else {
                    sum += 28;
                }
            } else if (i==4 || i==6 || i==9 || i==11) {
                sum += 30;
            } else {
                sum += 31;
            }
        }
        sum += 1;
        return sum;
    }
    
    // calculate how many days of the focal month
    private static int daysOfTheMonth(int[] date) {
        int days;
        if (date[1] == 2) {
            if (date[0] % 4 == 0 && date[0]%100 != 0 || date[0] % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        } else if (date[1]==4 || date[1]==6 || date[1]==9 || date[1]==11){
            days = 30;
        } else {
            days = 31;
        }
        return days;
    }

    // print the calendar
    private static void printCalendar(int[] date, int sumDays, int days) {
        System.out.println("-----------------------------");
        System.out.println(" Mon Tue Wed Thu Fri Sat Sun ");
        //print indentation of the week of the first day
        //know that Jan 1 1800 is Wednesday
        //therefore, sumDays should add 2 days and then mod 7
        //to calculate indentation should add 1 
        for (int i = 1; i <= (sumDays + 1) % 7; i++) {
            System.out.print("    ");
        }
        for (int i = 1; i <= days; i++) {
            if ((sumDays + 2) % 7 == 0) {
                String str = String.format("%4d",i);
                System.out.println(str);
            } else {
                String str = String.format("%4d",i);
                System.out.print (str);
            }
            sumDays++;
        }
        System.out.println();

    }

    // print the input month and year e.g. "Auguest 1975"
    private static void printDate(int[] date) {
        String [] months = new String[] {"January", "Frebruary", "March", "April", "May", "June",
                                        "July", "August", "September", "October", "November", "December"};
        int index = date[1];
        System.out.println(months[index-1] + " " + date[0]);
    }

}
