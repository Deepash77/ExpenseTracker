import java.io.*;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses;
    private final String FILE_NAME = "expenses.txt";

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Scanner scanner) {
        System.out.print("Enter category (e.g., Food, Travel): ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        expenses.add(new Expense(category, description, amount));
        System.out.println("Expense added.");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n--- Expense List ---");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    public void deleteExpense(Scanner scanner) {
        viewExpenses();
        if (expenses.isEmpty()) return;

        System.out.print("Enter the expense number to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (index >= 1 && index <= expenses.size()) {
            expenses.remove(index - 1);
            System.out.println("Expense deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void viewTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.println("Total Expenses: $" + total);
    }

    public void saveExpensesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.println(e.getCategory() + "," + e.getDescription() + "," + e.getAmount());
            }
            System.out.println("Expenses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file.");
        }
    }

    public void loadExpensesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                if (parts.length == 3) {
                    String category = parts[0];
                    String description = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    expenses.add(new Expense(category, description, amount));
                }
            }
            System.out.println("Expenses loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file.");
        }
    }
}
