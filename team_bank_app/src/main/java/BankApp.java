import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class BankApp {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        Bank bank=new Bank();

        while (true) {
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

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1: {
                    System.out.println("Enter your name: ");
                    String newName = scanner.nextLine();
                    System.out.println("Enter you surname: ");
                    String newSurname = scanner.nextLine();
                    System.out.println("Enter you gender : ");
                    String newGender = scanner.nextLine();
                    System.out.println("Enter you age: ");
                    int newAge = scanner.nextInt();
                    User user = new User(newName, newSurname, newGender, newAge);
                    users.add(user);
                    System.out.println("Your Username was added to list.");
                    break;
                }
                case 2: {
                    System.out.println("Enter your name: ");
                    String newName = scanner.nextLine();
                    System.out.println("Enter your surname: ");
                    String newSurname = scanner.nextLine();
                    System.out.println("Enter you gender : ");
                    String newGender = scanner.nextLine();
                    System.out.println("Enter you age: ");
                    int newAge = scanner.nextInt();
                    users.remove(new User(newName, newSurname, newGender, newAge));
                    System.out.println("Your Username was removed from list.");
                    break;
                }
                case 3: {
                    System.out.println("Welcome to our bank app ,you choose deposit option");
                    System.out.println("Please enter  desired amount :");
                    int depositAmount = scanner.nextInt();
                    bank.deposit(depositAmount);
                    break;
                }
                case 4: {
                    System.out.println("Welcome to our bank app ,you choose withdraw option");
                    System.out.println("Please enter  desired amount,with you wanna withdraw :");
                    int withdrawAmount = scanner.nextInt();
                    bank.withdraw(withdrawAmount);
                    break;
                }
                case 5: {
                    System.out.println("Current balance statement");
                    bank.printBalanceStatement();
                    break;
                }
                case 6: {
                    System.out.println("Your unique card Number is :  ");
                    Bank banks = new Bank();
                    banks.BankNumber();
                    break;
                }

                case 7: {
                    System.out.println("Users list: ");
                    for (User user : users) {
                        System.out.println(user);
                    }
                    System.out.println("Users list end.");
                    break;
                }
                case 8: {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
            System.out.println("");
        }
    }
}


