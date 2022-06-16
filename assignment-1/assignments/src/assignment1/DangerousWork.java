package assignment1;

import java.util.Scanner;

public class DangerousWork {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("How much would you like to earn? ");
        double expect_money = scan.nextDouble();
        
        boolean flag = ifTheWorkerIsGreed(expect_money);
        
        double salary = 0.01;
        double total_earn = 0.01;
        int day_count = 1;
        if (flag == false) {
            System.out.println("Sorry! You are dead anyway! No one can survive here more than 30 days...");
        } else {
            for (int i = 2; i <= 30; i++) {
                salary *= 2;
                total_earn += salary;
                day_count++;
                if (total_earn >= expect_money) {
                    System.out.println("You will have your money in " + day_count + " days.");
                    break;
                }
            }
        }
        scan.close();
    }

    private static Boolean ifTheWorkerIsGreed (double expect_money) {
        double salary = 0.01;
        double sum = 0.01;
        boolean flag;
        
        // Total salary 30 days can earn
        for (int i = 2; i <= 30; i++) {
			salary *= 2;
			sum += salary;
        }
        if (expect_money > sum) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }
    
}
