import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FestivalBonusCalculator {
    abstract static class Employee {
        final String name;
        final double salary;
        Employee(String name, double salary) { this.name = name; this.salary = salary; }
        abstract double bonus();
    }

    static class FullTime extends Employee {
        FullTime(String name, double salary) { super(name, salary); }
        double bonus() { return salary * 0.10; }
    }

    static class PartTime extends Employee {
        PartTime(String name, double salary) { super(name, salary); }
        double bonus() { return salary * 0.05; }
    }

    static class Intern extends Employee {
        Intern(String name, double salary) { super(name, salary); }
        double bonus() { return 2000; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String type = p[0], name = p[1];
            double salary = Double.parseDouble(p[2]);
            switch (type) {
                case "FULLTIME": employees.add(new FullTime(name, salary)); break;
                case "PARTTIME": employees.add(new PartTime(name, salary)); break;
                case "INTERN": employees.add(new Intern(name, salary)); break;
                default: throw new IllegalArgumentException("Unknown employee type: " + type);
            }
        }
        double total = 0;
        for (Employee employee : employees) {
            double bonus = employee.bonus();
            System.out.printf("%s: %.2f%n", employee.name, bonus);
            total += bonus;
        }
        System.out.printf("Total Bonus: %.2f%n", total);
        sc.close();
    }
}
