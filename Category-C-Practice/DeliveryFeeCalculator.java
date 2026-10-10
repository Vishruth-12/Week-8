import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryFeeCalculator {
    abstract static class Delivery {
        final double weight;
        final double distance;
        Delivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
        abstract double fee();
        abstract String type();
    }

    static class Standard extends Delivery {
        Standard(double weight, double distance) { super(weight, distance); }
        double fee() { return 5 + 0.50 * weight + 0.10 * distance; }
        String type() { return "STANDARD"; }
    }

    static class Express extends Delivery {
        Express(double weight, double distance) { super(weight, distance); }
        double fee() { return 15 + 1.00 * weight + 0.20 * distance; }
        String type() { return "EXPRESS"; }
    }

    static class International extends Delivery {
        final double customsFee;
        International(double weight, double distance, double customsFee) {
            super(weight, distance);
            this.customsFee = customsFee;
        }
        double fee() { return 25 + 2.00 * weight + 0.50 * distance + customsFee; }
        String type() { return "INTERNATIONAL"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double weight = Double.parseDouble(p[1]);
            double distance = Double.parseDouble(p[2]);
            switch (p[0]) {
                case "STANDARD": deliveries.add(new Standard(weight, distance)); break;
                case "EXPRESS": deliveries.add(new Express(weight, distance)); break;
                case "INTERNATIONAL":
                    deliveries.add(new International(weight, distance, Double.parseDouble(p[3]))); break;
                default: throw new IllegalArgumentException("Unknown delivery type: " + p[0]);
            }
        }
        double total = 0;
        for (Delivery delivery : deliveries) {
            double fee = delivery.fee();
            System.out.printf("%s: %.2f%n", delivery.type(), fee);
            total += fee;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
