import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.loadExpensesFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. View Total");
            System.out.println("5. Save & Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number between 1-5: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> manager.addExpense(scanner);
                case 2 -> manager.viewExpenses();
                case 3 -> manager.deleteExpense(scanner);
                case 4 -> manager.viewTotal();
                case 5 -> {
                    manager.saveExpensesToFile();
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
