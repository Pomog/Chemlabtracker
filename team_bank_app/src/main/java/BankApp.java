import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BankApp {
    public static void main(String[] args) {
        UsersDatabase database = new InMemoryUsersDatabase();
        Bank bank = new Bank();

        while (true) {
            printMenu();

            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1: {
                    addUsers(database);
                    break;
                }
                case 2: {
                    deleteUsers(database);
                    break;
                }
                case 3: {
                    depositMoney(bank);
                    break;
                }
                case 4: {
                    withdrawMoney(bank);
                    break;
                }
                case 5: {
                    printBalance(bank);
                    break;
                }
                case 6: {
                    uniqueCard();
                    break;
                }

                case 7: {
                    printUsers(database);
                    break;
                }
                case 8: {
                    exitFromApplication();
                }
            }
            System.out.println("");
        }
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());

    }

    private static void printMenu() {
        System.out.println("Program menu:");
        System.out.println("1. Create new username");
        System.out.println("2. Delete  username from list");
        System.out.println("3.Deposit money");
        System.out.println("4.Withdraw money");
        System.out.println("5.Current balance statement");
        System.out.println("6.Your  card number");
        System.out.println("7. Show all username in the list");
        System.out.println("8. Exit");

        System.out.println("");
    }

    private static void exitFromApplication() {
        System.out.println("Good by!");
        System.exit(0);
    }

    private static void printUsers(UsersDatabase database) {
        System.out.println("Users list: ");
        for (User user : database.getAllUsers()) {
            System.out.println(user);
        }
        System.out.println("Users list end.");
    }

    private static void uniqueCard() {
        System.out.println("Your unique card Number is :  ");
        Bank banks = new Bank();
        banks.BankNumber();
    }

    private static void printBalance(Bank bank) {
        System.out.println("Current balance statement");
        bank.printBalanceStatement();
    }

    private static void withdrawMoney(Bank bank) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our bank app ,you choose withdraw option");
        System.out.println("Please enter  desired amount,with you wanna withdraw :");
        int withdrawAmount = scanner.nextInt();
        bank.withdraw(withdrawAmount);
    }

    private static void depositMoney(Bank bank) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our bank app ,you choose deposit option");
        System.out.println("Please enter  desired amount :");
        int depositAmount = scanner.nextInt();
        bank.deposit(depositAmount);
    }

    private static void deleteUsers(UsersDatabase database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String newSurname = scanner.nextLine();
        System.out.println("Enter you gender : ");
        String newGender = scanner.nextLine();
        System.out.println("Enter you age: ");
        int newAge = scanner.nextInt();
        database.deleteUsers(new User(newName, newSurname, newGender, newAge));
        System.out.println("Your Username was removed from list.");

    }

    private static void addUsers(UsersDatabase database) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter you surname: ");
        String newSurname = scanner.nextLine();
        System.out.println("Enter you gender : ");
        String newGender = scanner.nextLine();
        System.out.println("Enter you age: ");
        int newAge = scanner.nextInt();
        User user = new User(newName, newSurname, newGender, newAge);
        database.addUsers(user);
        System.out.println("Your Username was added to list.");
    }
}


