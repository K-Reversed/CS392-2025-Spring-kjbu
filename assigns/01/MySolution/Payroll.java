/**
 * @author Kevin Jiang
 * @author Hongwei Xi
 * @version  v1.11, 4 Feb 2025
 */
import java.text.DecimalFormat;
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    DecimalFormat df = new DecimalFormat("#.00");

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

    public void print(){
        try {
            for (Employee person : people) {
                System.out.println("[" + person.name + ", #" + person.ID + ", $" + df.format(person.salary) + "]");
            }
        } catch (NullPointerException _) {}
    }

    public void add_employee(Employee newbie) {
        people[current_size] = newbie;
        System.out.println(newbie.name + " added with id #" + newbie.ID + ", salary is $" + df.format(newbie.salary));
        current_size++;
    }

    public void remove_employee(int i) throws EmployeeIndexException {
        if (i > people.length - 1 || people[i] == null) {
            throw new EmployeeIndexException();
        }
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
        boolean employeeFound = false;
        int employeeID = 0;
        for (int i = 0; i < people.length; i++) {
            if (people[i].name.equals(name)) {
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
