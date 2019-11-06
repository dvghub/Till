package nu.educom.warehouse.till.display;

import nu.educom.calculateChange.Money;
import nu.educom.warehouse.till.products.IProduct;
import nu.educom.warehouse.till.till.ITill;

import java.util.List;
import java.util.Scanner;

public class ConsoleTillDisplay implements ITillDisplay {
    private final ITill till;

    public ConsoleTillDisplay(ITill till) {
        this.till = till;
        // Install our self as display target
        till.setDisplayInterface(this);
    }

    public void run() {
        till.showAllProducts();
        showMenu();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.equals("q")) {
            switch(input.substring(0, 1)) {
                case "p":
                    till.showAllProducts();
                    break;

                case "b":
                    Money amount = new Money(Integer.parseInt(input.substring(1)));
                    var result = till.initiatePayment(amount);
                    if (result == null) {
                        System.out.println("Payment failed");
                        break;
                    } else if (result.size() == 0) {
                        System.out.println("Payment successful");
                    } else {
                        StringBuilder resultString = new StringBuilder();
                        for (var pair : result.entrySet()) {
                            if (resultString.length() != 0) {
                                resultString.append(" ");
                            }
                            resultString.append(String.format("%n%1s: %2d", pair.getKey().toString(), pair.getValue()));
                        }
                        System.out.printf("Payment done, please return: %s%n", resultString.toString());
                    }
                    break;
                default:
                    if (!till.handleBarcode(input)) {
                        System.out.println("Product could not be added");
                    }
                    break;
            }
            showMenu();
            input = scanner.nextLine().trim().toLowerCase();
        }
        scanner.close();
    }

    private void showMenu() {
        System.out.println("P         : Toon alle producten");
        System.out.println("<Barcode> : Scan de barcode code");
        System.out.println("B<bedrag> : Doe een betaling");
        System.out.println("Q         : Programma afbreken");
        System.out.print("> ");
    }

    @Override
    public void displayClientScreen(String line1, String line2) {
        System.out.println("==========================================");
        System.out.printf("|%-40s|%n", line1);
        System.out.printf("|%-40s|%n", line2);
        System.out.println("==========================================");
    }

    @Override
    public void displayProducts(String title, List<IProduct> products) {
        System.out.println("======================== " + title + " =======================");
        for (IProduct product : products) {
            System.out.printf("%-6s %-40s %s%n",
                              product.getBarcode(), product.getDescription(), product.getPrice().toString());
        }
        System.out.println("=========================================================");
    }
}
