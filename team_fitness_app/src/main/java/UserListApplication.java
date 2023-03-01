import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class UserListApplication {

    public static void main(String[] args) {
        UserListApplication userListApplication = new UserListApplication();
        List<User> userList = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Register new user");
            System.out.println("2. Login");
            System.out.println("3. Delete user");
            System.out.println("4. Exit");

            System.out.println();

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case 1 -> userListApplication.registerUser();
                case 2 -> userListApplication.login();
                case 3 -> userListApplication.deleteUser();
                case 4 -> {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }

        }

    }

    void registerUser() {

    }

    boolean login() {
        return false;
    }

    void deleteUser() {

    }
}
