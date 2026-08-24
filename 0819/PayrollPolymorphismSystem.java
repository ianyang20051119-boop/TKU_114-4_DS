abstract class Employee {
    protected String id;
    protected String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract double calculatePay();

    public String getName() {
        return name;
    }
}

class MonthlyEmployee extends Employee {
    private double monthlySalary;

    public MonthlyEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary < 0 ? 0 : monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hours;

    public HourlyEmployee(String id, String name, double hourlyRate, double hours) {
        super(id, name);
        this.hourlyRate = hourlyRate < 0 ? 0 : hourlyRate;
        this.hours = hours < 0 ? 0 : hours;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hours;
    }
}

class SalesEmployee extends Employee {
    private double baseSalary;
    private double sales;
    private double commissionRate;

    public SalesEmployee(String id, String name, double baseSalary,
                         double sales, double commissionRate) {
        super(id, name);
        this.baseSalary = baseSalary < 0 ? 0 : baseSalary;
        this.sales = sales < 0 ? 0 : sales;
        this.commissionRate = commissionRate < 0 ? 0 : commissionRate;
    }

    @Override
    public double calculatePay() {
        return baseSalary + sales * commissionRate;
    }
}

public class PayrollPolymorphismSystem {

    public static void main(String[] args) {

        Employee[] employees = {
            new MonthlyEmployee("E001", "Alice", 50000),
            new HourlyEmployee("E002", "Bob", 200, 160),
            new SalesEmployee("E003", "Charlie", 30000, 200000, 0.05),
            new MonthlyEmployee("E004", "David", 45000)
        };

        double totalPay = 0;
        double maxPay = 0;
        String highestEmployee = "";

        for (Employee employee : employees) {
            double pay = employee.calculatePay();

            System.out.println(
                employee.getName() + " 薪資：" + pay + " 元"
            );

            totalPay += pay;

            if (pay > maxPay) {
                maxPay = pay;
                highestEmployee = employee.getName();
            }
        }

        System.out.println();
        System.out.println("薪資總額：" + totalPay + " 元");
        System.out.println("最高薪資：" + maxPay + " 元");
        System.out.println("最高薪資員工：" + highestEmployee);
    }
}