//this code needs revision...

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserLogin {

    public static void printMenu(String[] options) {

        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();

        String[] options = {    "1 - Login",
                "2 - Register",
                "3 - Exit",
        };

        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 3) {
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register(users);
                        break;
                    case 3:
                        exit();
                }

            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and 3");
                scanner.next();
            }
        }
    }

    private static void login() {
        String Username;
        String Password;

        Username = "admin";
        Password = "password";

        System.out.println("User login");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter Username : ");
        String username = input1.next();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter Password : ");
        String password = input2.next();

        if (username.equals(Username) && password.equals(Password)) {
            System.out.println("Login successful!");
        }

        else if (username.equals(Username)) {
            System.out.println("Invalid Password!");
        } else if (password.equals(Password)) {
            System.out.println("Invalid Username!");
        } else {
            System.out.println("Invalid Username & Password!");
        }
    }

    private static void register(List<User> users) {
        System.out.println("User registration");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter login: ");
        String userLogin = scanner.nextLine();
        System.out.println("Enter password: ");
        String userPassword = scanner.nextLine();
        String userID = UUID.randomUUID().toString().substring(0, 8);
        User user = new User(userID, userLogin, userPassword);
        users.add(user);
        System.out.println("Registration complete!");
    }

    private static void exit() {
        System.out.println("Good bye!");
    }
}