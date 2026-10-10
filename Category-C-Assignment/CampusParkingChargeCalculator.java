import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampusParkingChargeCalculator {
    abstract static class Vehicle {
        final int hours;
        Vehicle(int hours) { this.hours = hours; }
        abstract double charge();
        abstract String type();
    }

    static class Bike extends Vehicle {
        Bike(int hours) { super(hours); }
        double charge() { return 10.0 * hours; }
        String type() { return "BIKE"; }
    }

    static class Car extends Vehicle {
        Car(int hours) { super(hours); }
        double charge() { return 30.0 + 20.0 * (hours - 1); }
        String type() { return "CAR"; }
    }

    static class Truck extends Vehicle {
        Truck(int hours) { super(hours); }
        double charge() { return Math.max(100.0, 50.0 * hours); }
        String type() { return "TRUCK"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int hours = Integer.parseInt(p[1]);
            switch (p[0]) {
                case "BIKE": vehicles.add(new Bike(hours)); break;
                case "CAR": vehicles.add(new Car(hours)); break;
                case "TRUCK": vehicles.add(new Truck(hours)); break;
                default: throw new IllegalArgumentException("Unknown vehicle type: " + p[0]);
            }
        }
        double total = 0;
        for (Vehicle vehicle : vehicles) {
            double amount = vehicle.charge();
            System.out.printf("%s: %.2f%n", vehicle.type(), amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
