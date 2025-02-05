/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.11, Feb 2 2025
 */

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

        payrollA.print();
        System.out.println("__________________________________________________");

        payrollB.print();

        String inputA;
        String inputB;
        int selection;
        int payroll;
        int id = 0;

        do {
            payroll = 0;
            System.out.print("""
                    __________________________________________________
                    Select a payroll:
                    1. Payroll A
                    2. Payroll B
                    3. Terminate Program
                    Please type a number:\s""");
            inputA = input.next();
            try {
                payroll = Integer.parseInt(inputA);

            } catch (NumberFormatException e) {
                System.out.println("Input is not a number");
                TimeUnit.SECONDS.sleep(2);
                continue;
            }

            switch (payroll) {
                case 1:
                    System.out.println("Editing Payroll A");
                    break;
                case 2:
                    System.out.println("Editing Payroll B");
                    break;
                case 3:
                    System.out.println("Terminating Program");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("__________________________________________________");
                    continue;
                default:
                    System.out.println("Input is outside range");
                    TimeUnit.SECONDS.sleep(2);
                    continue;
            }

            do {
                selection = 0;
                System.out.print("""
                        __________________________________________________
                        Choose an action:
                        1. Current number of employees
                        2. All employee information
                        3. Add employee
                        4. Remove employee
                        5. Find employee
                        6. Copy Payroll
                        7. Add Payroll
                        8. Back
                        Please type a number:\s""");
                inputB = input.next();
                try {
                    selection = Integer.parseInt(inputB);
                } catch (NumberFormatException e) {
                    System.out.println("Input is not a number");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("__________________________________________________");
                }
                switch (selection) {
                    case 1:
                        if (payroll == 1) {
                            System.out.println(payrollA.size());
                        }
                        if (payroll == 2) {
                            System.out.println(payrollB.size());
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 2:
                        if (payroll == 1) {
                            payrollA.print();
                        }
                        if (payroll == 2) {
                            payrollB.print();
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 3:
                        Employee em = new Employee();
                        System.out.print("Enter a name: ");
                        em.name = input.next();
                        System.out.print("Enter a salary: ");
                        em.salary = input.nextDouble();
                        if (payroll == 1) {
                            em.ID = payrollA.size() + 1;
                            payrollA.add_employee(em);
                        }
                        if (payroll == 2) {
                            em.ID = payrollB.size() + 1;
                            payrollB.add_employee(em);
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 4:
                        System.out.print("Enter the employee's id: ");
                        id = input.nextInt();
                        try {
                            if (payroll == 1) {
                                payrollA.remove_employee(id);
                            }
                            if (payroll == 2) {
                                payrollB.remove_employee(id);
                            }
                        } catch (EmployeeIndexException e) {
                            System.out.println("Employee #" + id + " not found");
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 5:
                        System.out.print("Enter the employee's name: ");
                        String name = input.next();
                        try {
                            if (payroll == 1) {
                                id = payrollA.find_employee(name);
                            }
                            if (payroll == 2) {
                                id = payrollB.find_employee(name);
                            }
                            System.out.println("Employee id: " + id + 1);
                        } catch (EmployeeNotFoundException e) {
                            System.out.println("Employee \" " + name + "\"not found");
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 6:
                        if (payroll == 1) {
                            payrollA.copy_payroll(payrollB);
                            System.out.println("Payroll B has been copied onto Payroll A");
                        }
                        if (payroll == 2) {
                            payrollB.copy_payroll(payrollA);
                            System.out.println("Payroll A has been copied onto Payroll B");
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 7:
                        if (payroll == 1) {
                            payrollA.add_payroll(payrollB);
                            System.out.println("Payroll B has been appended onto Payroll A");
                        }
                        if (payroll == 2) {
                            payrollB.add_payroll(payrollA);
                            System.out.println("Payroll A has been appended onto Payroll B");
                        }
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    case 8:
                        System.out.println("Returning to payroll selection");
                        TimeUnit.SECONDS.sleep(2);
                        break;
                    default:
                        System.out.println("Input is outside range");
                        TimeUnit.SECONDS.sleep(2);
                        break;

                }
            } while (selection != 8);
        } while (payroll != 3);


        input.close();
    }
}
