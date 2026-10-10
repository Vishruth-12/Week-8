import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamingPlanRenewalReminder {
    static abstract class Plan {
        final String name;
        final LocalDate startDate;
        Plan(String name, LocalDate startDate) { this.name = name; this.startDate = startDate; }
        abstract int validityDays();
        LocalDate renewalDate() { return startDate.plusDays(validityDays()); }
    }

    static class Basic extends Plan {
        Basic(String name, LocalDate date) { super(name, date); }
        int validityDays() { return 30; }
    }

    static class Standard extends Plan {
        Standard(String name, LocalDate date) { super(name, date); }
        int validityDays() { return 90; }
    }

    static class Premium extends Plan {
        Premium(String name, LocalDate date) { super(name, date); }
        int validityDays() { return 365; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Plan> plans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            String name = p[1];
            LocalDate date = LocalDate.parse(p[2]);
            switch (p[0]) {
                case "BASIC": plans.add(new Basic(name, date)); break;
                case "STANDARD": plans.add(new Standard(name, date)); break;
                case "PREMIUM": plans.add(new Premium(name, date)); break;
                default: throw new IllegalArgumentException("Unknown plan type: " + p[0]);
            }
        }
        for (Plan plan : plans) {
            System.out.println(plan.name + ": " + plan.renewalDate());
        }
        sc.close();
    }
}
