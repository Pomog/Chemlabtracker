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
                case 1 -> userListApplication.registerUser(userList);
                case 2 -> userListApplication.login(userList);
                case 3 -> userListApplication.deleteUser(userList);
                case 4 -> {
                    System.out.println("Good bye!");
                    System.exit(0);
                }
            }
        }
    }

    void registerUser(List userList) {
        UserInput userInput = scanUserInput();
        Registration registration = new Registration(userList);
        registration.registerUser(userInput.username, userInput.password);
    }

    void login(List<User> userList) {
        UserInput userInput = scanUserInput();
        for (User user: userList) {
           boolean userHasRecord = user.getUsername().equals(userInput.username)
                   && user.getPassword().equals(userInput.password);
           if(userHasRecord) {
               System.out.println("Login successful!\n");
               return;
           }
        }
        System.out.println("Login / password is incorrect, try again!\n");
    }

    void deleteUser(List<User> userList) {
        UserInput userInput = scanUserInput();
        boolean userHasRecord = false;
        for (User user: userList) {
             userHasRecord = user.getUsername().equals(userInput.username)
                    && user.getPassword().equals(userInput.password);

            if(userHasRecord){
                userList.remove(user);
                System.out.println("Your record was removed from list.\n");
                return;
            }
        }
        System.out.println("Login / password is incorrect, try again!\n");
    }

     public UserInput scanUserInput() {
         UserInput userInput = new UserInput();
         Scanner scanner = new Scanner(System.in);
         System.out.println("Enter username: ");
         userInput.username = scanner.nextLine();
         System.out.println("Enter password: ");
         userInput.password = scanner.nextLine();
         return userInput;
    }

     class UserInput {
        private String username;
        private String password;
    }
}
