import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryItemDueDateCalculator {
    abstract static class LibraryItem {
        final String title;
        LibraryItem(String title) { this.title = title; }
        abstract int loanDays();
        LocalDate dueDate(LocalDate borrowedOn) { return borrowedOn.plusDays(loanDays()); }
    }

    static class Book extends LibraryItem {
        Book(String title) { super(title); }
        int loanDays() { return 14; }
    }

    static class DVD extends LibraryItem {
        DVD(String title) { super(title); }
        int loanDays() { return 7; }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title) { super(title); }
        int loanDays() { return 3; }
    }

    static String unquote(String title) {
        title = title.trim();
        if (title.length() >= 2 && title.startsWith("\"") && title.endsWith("\"")) {
            return title.substring(1, title.length() - 1);
        }
        return title;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        LocalDate currentDate = LocalDate.of(2023, 10, 26);
        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            int space = line.indexOf(' ');
            String type = line.substring(0, space);
            String title = unquote(line.substring(space + 1));
            switch (type) {
                case "BOOK": items.add(new Book(title)); break;
                case "DVD": items.add(new DVD(title)); break;
                case "MAGAZINE": items.add(new Magazine(title)); break;
                default: throw new IllegalArgumentException("Unknown item type: " + type);
            }
        }
        for (LibraryItem item : items) {
            System.out.println(item.title + ": " + item.dueDate(currentDate));
        }
        sc.close();
    }
}
