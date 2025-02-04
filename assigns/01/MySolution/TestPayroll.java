/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.11, Feb 2 2025
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestPayroll {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        int upper = 60000;
        int lower = 20000;

        Payroll payrollA = new Payroll();
        Payroll payrollB = new Payroll();

        String[] namesA = {"A", "B", "C", "D", "E", "F"};
        String[] namesB = {"Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf"};

        for (String name : namesA) {
            Employee e = new Employee();
            e.name = name;
            e.ID = payrollA.size() + 1;
            e.salary = r.nextDouble(upper - lower) + lower;
            payrollA.add_employee(e);
        }

        for (String name : namesB) {
            Employee e = new Employee();
            e.name = name;
            e.ID = payrollB.size() + 1;
            e.salary = r.nextDouble(upper - lower) + lower;
            payrollB.add_employee(e);
        }

        System.out.println("__________________________________________________");
        payrollA.print();

        System.out.println("__________________________________________________");
        payrollB.print();

        int selection = 0;
        do {
            System.out.print("""
                    Choose an action:
                    1. S 
                    """);

            try {
                selection = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input was not a number");
                TimeUnit.SECONDS.sleep(2);
            }

        } while (selection != 8);


        input.close();
    }
}
