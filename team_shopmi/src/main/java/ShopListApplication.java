import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopListApplication {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<User> users = new ArrayList<>();


        while (true) {

            System.out.println("=================================");
            System.out.println("         Program Menu:           ");
            System.out.println("=================================");
            System.out.println("1. Register User");
            System.out.println("2. Add Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Products");
            System.out.println("5. Exit");

            System.out.println("");

            System.out.println("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice){
                case 1: {
                    System.out.println("Enter user ID: ");
                    Long userID = scanner.nextLong();
                    System.out.println("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();
                    User newUser = new User(userID, username, password);
                    users.add(newUser);
                    System.out.println("Registration successfully completed !");
                    break;
                }
                case 2: {
                    System.out.println("Enter product ID: ");
                    int productID = scanner.nextInt();
                    System.out.println("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter product price: ");
                    BigDecimal price = scanner.nextBigDecimal();
                    System.out.println("Enter product category: ");
                    String category = scanner.nextLine();
                    Product newProduct = new Product(productID,name,price,category);
                    products.add(newProduct);
                    System.out.println("Product added !");
                    break;
                }
                case 3: {
                    System.out.println("Enter product ID: ");
                    int productID = scanner.nextInt();
                   scanner.nextLine();

                   for (int i = 0;i < products.size(); i++){
                       Product newProduct = products.get(i);
                       if (newProduct.getProductID() == productID){
                           products.remove(i);
                           System.out.println("Product deleted.");
                           return;
                       }
                   }
                    System.out.println("Product not found.");
                }
                case 4: {
                    System.out.println("List all products: ");
                    for (Product newProduct : products){
                        System.out.println(newProduct);
                    }
                    System.out.println("Product list end. ");
                    break;
                }
                case 5: {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
        }
    }
}
