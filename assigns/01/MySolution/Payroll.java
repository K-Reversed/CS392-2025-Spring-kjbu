import java.text.DecimalFormat;

/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.1, 4 Feb 2025
 */

public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    DecimalFormat df = new DecimalFormat("#.00");
    int payroll = 20;

    public Payroll() {
        maximum_size = INITIAL_MAXIMUM_SIZE;
        current_size = 0;
        people = new Employee[maximum_size];
    }

    /**
     * Returns the current amount of employees on the payroll
     *
     * @since v1.0
     * @return Current number of employees
     */
    public int size(){
        return current_size;
    }

    /**
     * Prints all employees on the payroll
     *
     * @since v1.1
     */
    public void print(){
        try {
            for (Employee person : people) {
                System.out.println("[Name: " + person.name + " ID: " + person.ID + " Salary: $" + df.format(person.salary) + "]");
            }
        } catch (NullPointerException _) {
            System.out.println("End of payroll");
        }

    }

    /**
     * Adds a new employee to the end of a payroll
     *
     * @since v1.1
     * @param newbie employee object
     */
    public void add_employee(Employee newbie) {
        people[newbie.ID] = newbie;
        newbie.salary = payroll;
        System.out.println("Employee " + newbie.name + " has been added!" +
                "\nTheir id is " + current_size + " and their salary is $" + String.format("%.2f%n", newbie.salary));
        current_size++;
    }

    /**
     * Removes an employee from the payroll when an id is entered, and throws an error if the entered id is larger than the max payroll size, is less than 0, or if there are no employees at the id location
     *
     * @since v1.1
     * @param i id
     */
    public void remove_employee(int i) throws EmployeeIndexException {
        if (i > people.length - 1 || i < 0 || people[i] == null) {
            throw new EmployeeIndexException();
        }
        String name = people[i].name;
        people[i] = null;
        try {
            for (int j = i; j < people.length; j++) {
                people[j] = people[j + 1];
                people[j].ID = j - 1;
            }
            System.out.println("Employee #" + i +", " + name +" has been removed.");
        } catch (ArrayIndexOutOfBoundsException | NullPointerException _){}
        current_size--;
    }

    /**
     * Searches for an employee given the name, and returns the employee's id, if no employee is found, an error is thrown.
     *
     * @param name employee name
     * @return employee id
     */
    public int find_employee(String name) throws EmployeeNotFoundException {
        boolean employeeFound = false;
        int employeeID = 0;
        try {
            for (int i = 0; i < people.length; i++) {
                if (people[i].name.equals(name)) {
                    System.out.println("Employee \"" + name + "\" Found!");
                    employeeFound = true;
                    employeeID = i;
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException _) {}
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
