/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.0, Feb 2 2025
 */

import java.util.Arrays;
import java.util.ArrayList;

public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    public Payroll() {
        int payroll = 20;
        maximum_size = INITIAL_MAXIMUM_SIZE;
        current_size = 0;
    }

    /**
     * @since v1.0
     * @return Current number of employees
     */
    public int size(){
        return current_size;
    }

    public void print(){
        for (Employee person : people) {
            System.out.println(person);
        }
    }

    public void add_employee(Employee newbie) {

    }

    public void remove_employee(int i) throws EmployeeIndexException {
        if (i > people.length - 1) {
            throw new EmployeeIndexException();
        }
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
        boolean employeeFound = false;
        int employeeID = 0;
        for (int i = 0; i < people.length; i++) {
            if (people[i].toString().equals(name)) {
                System.out.println("Employee " + name + " Found!");
                employeeFound = true;
                employeeID = i;
            }
        }
        if (!employeeFound) {
            throw new EmployeeNotFoundException();
        }
        return employeeID;
    }

    public void copy_payroll(Payroll source) {

    }

    public void add_payroll(Payroll source) {

    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
}
