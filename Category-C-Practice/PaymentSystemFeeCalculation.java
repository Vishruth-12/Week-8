import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentSystemFeeCalculation {
    abstract static class Payment {
        protected final double amount;
        Payment(double amount) { this.amount = amount; }
        abstract double finalAmount();
        abstract String type();
    }

    static class Card extends Payment {
        Card(double amount) { super(amount); }
        double finalAmount() { return amount * 1.02; }
        String type() { return "CARD"; }
    }

    static class Wallet extends Payment {
        Wallet(double amount) { super(amount); }
        double finalAmount() { return amount * 1.01; }
        String type() { return "WALLET"; }
    }

    static class BankTransfer extends Payment {
        BankTransfer(double amount) { super(amount); }
        double finalAmount() { return amount; }
        String type() { return "BANKTRANSFER"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            String type = parts[0];
            double amount = Double.parseDouble(parts[1]);
            switch (type) {
                case "CARD": payments.add(new Card(amount)); break;
                case "WALLET": payments.add(new Wallet(amount)); break;
                case "BANKTRANSFER": payments.add(new BankTransfer(amount)); break;
                default: throw new IllegalArgumentException("Unknown payment type: " + type);
            }
        }
        double total = 0;
        for (Payment payment : payments) {
            double adjusted = payment.finalAmount();
            System.out.printf("%s: %.2f%n", payment.type(), adjusted);
            total += adjusted;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
