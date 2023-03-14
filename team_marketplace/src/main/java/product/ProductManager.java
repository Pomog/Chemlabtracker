package product;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    private static ArrayList<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


        public static void main(String[] args) {
            int option;
            do {
                System.out.println("Please select an option:");
                System.out.println("1. Add new product");
                System.out.println("2. Delete a product");
                System.out.println("3. List all products");
                System.out.println("0. Exit");
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (option) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        deleteProduct();
                        break;
                    case 3:
                        listProducts();
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                        break;
                }
            } while (option != 0);
        }

        private static void addProduct() {
            System.out.println("Please enter the product details:");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Category: ");
            String category = scanner.nextLine();

            Product newProduct = new Product(id, name, description, price, category);
            products.add(newProduct);
            System.out.println("New product added.");
        }

        private static void deleteProduct() {
            System.out.println("Please enter the ID of the product you wish to delete:");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                if (product.getId() == id) {
                    products.remove(i);
                    System.out.println("Product deleted.");
                    return;
                }
            }
            System.out.println("Product not found.");
        }

        private static void listProducts() {
            System.out.println("List of products:");
            for (Product product : products) {
                System.out.println(product.getId() + ": " + product.getName() + " - " + product.getDescription() +
                        " (" + product.getCategory() + ") - $" + product.getPrice());
            }
        }
}
