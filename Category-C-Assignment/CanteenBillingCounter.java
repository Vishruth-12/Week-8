import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CanteenBillingCounter {
    abstract static class Bill {
        final double amount;
        Bill(double amount) { this.amount = amount; }
        abstract double finalAmount();
        abstract String type();
    }

    static class StudentBill extends Bill {
        StudentBill(double amount) { super(amount); }
        double finalAmount() { return amount * 0.90; }
        String type() { return "STUDENT"; }
    }

    static class StaffBill extends Bill {
        StaffBill(double amount) { super(amount); }
        double finalAmount() { return amount * 0.95; }
        String type() { return "STAFF"; }
    }

    static class GuestBill extends Bill {
        GuestBill(double amount) { super(amount); }
        double finalAmount() { return amount + 10; }
        String type() { return "GUEST"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Bill> bills = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            double amount = Double.parseDouble(p[1]);
            switch (p[0]) {
                case "STUDENT": bills.add(new StudentBill(amount)); break;
                case "STAFF": bills.add(new StaffBill(amount)); break;
                case "GUEST": bills.add(new GuestBill(amount)); break;
                default: throw new IllegalArgumentException("Unknown customer type: " + p[0]);
            }
        }
        double total = 0;
        for (Bill bill : bills) {
            double amount = bill.finalAmount();
            System.out.printf("%s: %.2f%n", bill.type(), amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
