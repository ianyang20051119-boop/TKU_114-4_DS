abstract class EmployeeBase {

    protected String id;
    protected String name;

    public EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase");
    }

    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {

    public FullTimeEmployee(String id, String name) {
        super(id, name);
        System.out.println("FullTimeEmployee");
    }

    @Override
    public double calculatePay() {
        return 50000;
    }
}

class PartTimeEmployee extends EmployeeBase {

    public PartTimeEmployee(String id, String name) {
        super(id, name);
        System.out.println("PartTimeEmployee");
    }

    @Override
    public double calculatePay() {
        return 20000;
    }
}

public class EmployeeConstructorChain {

    public static void main(String[] args) {

        FullTimeEmployee fullTime = new FullTimeEmployee("E001", "Alice");
        System.out.println("FullTime Pay: " + fullTime.calculatePay());

        System.out.println();

        PartTimeEmployee partTime = new PartTimeEmployee("E002", "Bob");
        System.out.println("PartTime Pay: " + partTime.calculatePay());

        System.out.println();
        System.out.println("Constructor 執行順序：");
        System.out.println("FullTimeEmployee → EmployeeBase → FullTimeEmployee");
        System.out.println("PartTimeEmployee → EmployeeBase → PartTimeEmployee");
    }
}