/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.11, 4 Feb 2025
 */
import java.text.DecimalFormat;
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    DecimalFormat df = new DecimalFormat(",###.00");

    public Payroll() {
        maximum_size = INITIAL_MAXIMUM_SIZE;
        current_size = 0;
        people = new Employee[maximum_size];
    }

    /**
     * @since v1.0
     * @return Current number of employees
     */
    public int size(){
        return current_size;
    }

    /**
     * @since v1.11
     */
    public void print(){
        try {
            for (Employee person : people) {
                System.out.println("[" + person.name + ", #" + person.ID + ", $" + df.format(person.salary) + "]");
            }
        } catch (NullPointerException _) {}
    }

    /**
     * @since v1.11
     * @param newbie employee object
     */
    public void add_employee(Employee newbie) {
        people[current_size] = newbie;
        System.out.println(newbie.name + " added with id #" + newbie.ID + ", salary is $" + df.format(newbie.salary));
        current_size++;
    }

    /**
     * @since v1.11
     * @param i id
     */
    public void remove_employee(int i) throws EmployeeIndexException {
        if (current_size == 0){
            System.out.println("There are no employees to remove");
            return;
        }
        if (i > people.length - 1 || people[i] == null || i == 0) {
            throw new EmployeeIndexException();
        }

        String name = people[i - 1].name;
        people[i - 1] = null;

        try {
            for (int j = i - 1; j < people.length; j++) {
                people[j] = people[j + 1];
                people[j].ID--;
            }
        } catch (NullPointerException | IndexOutOfBoundsException _) {
            System.out.println("Employee #" + i + ", \"" + name + "\" has been removed from the payroll");
        }

        current_size--;
    }

    /**
     * @since v1.11
     * @param name employee's name
     * @return id number of the employee
     */
    public int find_employee(String name) throws EmployeeNotFoundException {
        boolean employeeFound = false;
        int employeeID = 0;

        try {
            for (int i = 0; i < people.length; i++) {
                if (people[i].name.equals(name)) {
                    System.out.println("Employee " + name + " Found!");
                    employeeFound = true;
                    employeeID = i;
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException _) {}
        if (!employeeFound) {
            throw new EmployeeNotFoundException();
        }
        return employeeID;
    }

    /**
     * @since v1.11
     * @param source copies the information of one Payroll to another
     */
    public void copy_payroll(Payroll source) {
        for (int i = 0; i < source.size() - 1; i++) {
            people[i] = source.people[i];
        }
        current_size = source.current_size;
    }

    /**
     * @since v1.11
     * @param source appends the data of one payroll to another
     */
    public void add_payroll(Payroll source) {
        for (int i = 0; i < source.size(); i++) {
            people[i + current_size] = source.people[i];
            people[i + current_size].ID += current_size;
        }
        current_size += source.current_size;
    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
}