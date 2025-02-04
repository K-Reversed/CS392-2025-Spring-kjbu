/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.0, Feb 2 2025
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestPayroll {
    public static void main(String[] args) throws InterruptedException {
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
                    TimeUnit.SECONDS.sleep(2);
                    continue;
                case 2:
                    if (payroll.size() == 0) {
                        System.out.println("There is no employee data!");
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    }
                    payroll.print();
                    TimeUnit.SECONDS.sleep(2);
                    continue;
                case 3:
                    Employee employee = new Employee();
                    employee.ID = payroll.size();
                    System.out.print("Enter the new employee's name: ");
                    employee.name = input.next();
                    payroll.add_employee(employee);
                    TimeUnit.SECONDS.sleep(2);
                    continue;
                case 4:
                    if (payroll.size() == 0) {
                        System.out.println("There are no employees to remove!");
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    }
                    try {
                        System.out.print("Please enter the employee's id: ");
                        id = input.nextInt();
                        payroll.remove_employee(id);
                        continue;
                    } catch (EmployeeIndexException e) {
                        System.out.println("Employee not found or does not exist!");
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    } catch (InputMismatchException e) {
                        System.out.println("Not a number!");
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    }
                case 5:
                    if (payroll.size() == 0) {
                        System.out.println("There is no employee data!");
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    }
                    System.out.print("Please enter the employee's name: ");
                    name = input.next();
                    try {
                        id = payroll.find_employee(name);
                        System.out.println("Employee ID: " + id);
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Employee not found!");
                        TimeUnit.SECONDS.sleep(2);
                        continue;
                    }
                case 6:
                    payroll.copy_payroll(payroll);
                case 7:
                    payroll.add_payroll(payroll);
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
