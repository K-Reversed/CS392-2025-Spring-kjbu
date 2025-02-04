/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.0, Feb 2 2025
 */

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestPayroll {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner input = new Scanner(System.in);

        Payroll payroll = new Payroll();

        String inputNumber;
        String name;
        int selection;
        int id;

        do {
            System.out.println("""
                    What would you like to do?
                    1. View number of employees
                    2. Print all employee information
                    3. Add an employee
                    4. Remove an employee
                    5. Find an employee
                    6. Copy payroll
                    7. Add payroll
                    8. Terminate Program""");
            System.out.print("Please enter a valid number: ");
            id = 0;
            inputNumber = input.next();

            try {
                selection = Integer.parseInt(inputNumber);
            } catch (NumberFormatException e) {
                System.out.println("The inputted value is not a number, please try again.");
                TimeUnit.SECONDS.sleep(2);
                continue;
            }
            switch (selection) {
                case 1:
                    System.out.println(payroll.size());
                    System.out.println("Press enter to continue:");
                    System.in.read();
                    continue;
                case 2:
                case 3:
                case 4:
                    try {
                        System.out.print("Please enter the employee's id: ");
                        id = input.nextInt();
                        payroll.remove_employee(id);
                    } catch (EmployeeIndexException e) {
                        System.out.println("Employee not found!");
                        System.out.println("Press enter to continue:");
                        System.in.read();
                        continue;
                    } catch (InputMismatchException e) {
                        System.out.println("Not a number!");
                        System.out.println("Press enter to continue:");
                        System.in.read();
                        continue;
                    }
                case 5:
                    name = input.next("Please enter the employee's name");
                    try {
                        id = payroll.find_employee(name);
                        System.out.println("Employee ID: " + id);
                        System.out.println("Press enter to continue:");
                        System.in.read();
                        continue;
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Employee not found!");
                        System.out.println("Press enter to continue:");
                        System.in.read();
                        continue;
                    }
                case 6:
                case 7:
                    System.out.println("7");
                case 8:
                    System.out.println("Terminating program");
                    TimeUnit.SECONDS.sleep(2);
                    System.exit(0);
                default:
                    System.out.println("The number is outside the program range, please input a different number.");
                    TimeUnit.SECONDS.sleep(2);
            }
        } while (true);
    }
}
