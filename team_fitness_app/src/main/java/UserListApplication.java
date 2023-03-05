import java.util.Scanner;

class UserListApplication {

    public static void main(String[] args) {
        Database database = new InMemoryDatabaseImpl();
        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(database, menuNumber);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Register new user.");
        System.out.println("2. Login.");
        System.out.println("3. Delete user from database.");
        System.out.println("4. Exit");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(Database database, int selectedMenu) {
        switch (selectedMenu) {
            case 1 -> registerUser(database);
            case 2 -> validateUser(database);
            case 3 -> removeUser(database);
            case 4 -> exitProgramAction();
        }
    }

    private static void exitProgramAction() {
        System.out.println("Good by!");
        System.exit(0);
    }

    private static void registerUser(Database database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        database.registerNewUser(user);
        System.out.println("User registered with user ID " + user.getId());
    }

    private static boolean validateUser(Database database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        return database.login(id, password);
    }

    private static void removeUser(Database database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        database.deleteUser(id, password);
        System.out.println("User was removed from database.");
    }
}
