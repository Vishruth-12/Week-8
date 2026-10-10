import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HostelElectricityBill {
    abstract static class Room {
        final int units;
        Room(int units) { this.units = units; }
        abstract double bill();
        abstract String type();
    }

    static class SingleRoom extends Room {
        SingleRoom(int units) { super(units); }
        double bill() { return 8.0 * units; }
        String type() { return "SINGLE"; }
    }

    static class SharedRoom extends Room {
        final int occupants;
        SharedRoom(int units, int occupants) { super(units); this.occupants = occupants; }
        double bill() { return 6.0 * units / occupants; }
        String type() { return "SHARED"; }
    }

    static class AcRoom extends Room {
        AcRoom(int units) { super(units); }
        double bill() { return 10.0 * units + 200; }
        String type() { return "AC"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            int units = Integer.parseInt(p[1]);
            switch (p[0]) {
                case "SINGLE": rooms.add(new SingleRoom(units)); break;
                case "SHARED": rooms.add(new SharedRoom(units, Integer.parseInt(p[2]))); break;
                case "AC": rooms.add(new AcRoom(units)); break;
                default: throw new IllegalArgumentException("Unknown room type: " + p[0]);
            }
        }
        double total = 0;
        for (Room room : rooms) {
            double amount = room.bill();
            System.out.printf("%s: %.2f%n", room.type(), amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
