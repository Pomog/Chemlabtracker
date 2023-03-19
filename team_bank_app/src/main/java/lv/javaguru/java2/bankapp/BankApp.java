package lv.javaguru.java2.bankapp;

import lv.javaguru.java2.bankapp.bank_service.DepositMoneyService;
import lv.javaguru.java2.bankapp.bank_service.PrintBalanceService;
import lv.javaguru.java2.bankapp.bank_service.UniqueCardService;
import lv.javaguru.java2.bankapp.bank_service.WithdrawMoneyService;
import lv.javaguru.java2.bankapp.console_ui.*;
import lv.javaguru.java2.bankapp.database.InMemoryUsersDatabase;
import lv.javaguru.java2.bankapp.database.UsersDatabase;
import lv.javaguru.java2.bankapp.domain.Bank;
import lv.javaguru.java2.bankapp.services.AddUsersService;
import lv.javaguru.java2.bankapp.services.DeleteUsersService;
import lv.javaguru.java2.bankapp.services.PrintUsersService;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        UsersDatabase database = new InMemoryUsersDatabase();

        AddUsersService addUsersService = new AddUsersService(database);
        UIAction addUsersAction = new AddUsersUIAction(addUsersService);

        DeleteUsersService deleteUsersService = new DeleteUsersService(database);
        UIAction deleteUsersAction = new DeleteUsersUIAction(deleteUsersService);
        PrintUsersService printUsersService = new PrintUsersService(database);
        UIAction printUsersAction = new PrintUsersAction(printUsersService);

        UIAction exitApplication = new ExitApplicationAction();

        Bank bank = new Bank();
        WithdrawMoneyService withdrawMoneyService = new WithdrawMoneyService();
        DepositMoneyService depositMoneyService = new DepositMoneyService();
        PrintBalanceService printBalanceService = new PrintBalanceService();
        UniqueCardService uniqueCardService = new UniqueCardService();

        while (true) {
            printMenu();

            int userChoice = getUserChoice();

            switch (userChoice) {
                case 1: {
                    addUsersAction.execute();
                    break;
                }
                case 2: {
                    deleteUsersAction.execute();
                    break;
                }
                case 3: {
                    depositMoneyService.depositMoney(bank);
                    break;
                }
                case 4: {
                    withdrawMoneyService.withdrawMoney(bank);
                    break;
                }
                case 5: {
                    printBalanceService.printBalance(bank);
                    break;
                }
                case 6: {
                    uniqueCardService.uniqueCard();
                    break;
                }

                case 7: {
                    printUsersAction.execute();
                    break;
                }
                case 8: {
                    exitApplication.execute();
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


}







