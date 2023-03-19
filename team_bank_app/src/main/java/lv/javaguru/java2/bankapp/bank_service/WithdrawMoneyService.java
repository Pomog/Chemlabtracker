package lv.javaguru.java2.bankapp.bank_service;

import lv.javaguru.java2.bankapp.domain.Bank;

import java.util.Scanner;

public class WithdrawMoneyService {

    public void withdrawMoney(Bank bank){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our bank app ,you choose withdraw option");
        System.out.println("Please enter  desired amount,with you wanna withdraw :");
        int withdrawAmount = scanner.nextInt();
        bank.withdraw(withdrawAmount);
    }
}
