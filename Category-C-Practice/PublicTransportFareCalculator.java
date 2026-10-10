import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublicTransportFareCalculator {
    abstract static class Journey {
        final double distance;
        Journey(double distance) { this.distance = distance; }
        abstract double fare();
        abstract String type();
    }

    static class Bus extends Journey {
        Bus(double distance) { super(distance); }
        double fare() { return Math.min(10.0, 2 + 0.10 * distance); }
        String type() { return "BUS"; }
    }

    static class Train extends Journey {
        Train(double distance) { super(distance); }
        double fare() { return 3 + 0.15 * distance; }
        String type() { return "TRAIN"; }
    }

    static class Metro extends Journey {
        final double peakHourFactor;
        Metro(double distance, double peakHourFactor) {
            super(distance);
            this.peakHourFactor = peakHourFactor;
        }
        double fare() { return (1.50 + 0.20 * distance) * peakHourFactor; }
        String type() { return "METRO"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Journey> journeys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double distance = Double.parseDouble(p[1]);
            switch (p[0]) {
                case "BUS": journeys.add(new Bus(distance)); break;
                case "TRAIN": journeys.add(new Train(distance)); break;
                case "METRO": journeys.add(new Metro(distance, Double.parseDouble(p[2]))); break;
                default: throw new IllegalArgumentException("Unknown transport type: " + p[0]);
            }
        }
        double total = 0;
        for (Journey journey : journeys) {
            double fare = journey.fare();
            System.out.printf("%s: %.2f%n", journey.type(), fare);
            total += fare;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
