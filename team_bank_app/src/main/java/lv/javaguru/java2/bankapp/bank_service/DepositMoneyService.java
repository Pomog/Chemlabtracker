package lv.javaguru.java2.bankapp.bank_service;

import lv.javaguru.java2.bankapp.domain.Bank;

import java.util.Scanner;

public class DepositMoneyService {
   public void depositMoney(Bank bank){
       Scanner scanner = new Scanner(System.in);
       System.out.println("Welcome to our bank app ,you choose deposit option");
       System.out.println("Please enter  desired amount :");
       int depositAmount = scanner.nextInt();
       bank.deposit(depositAmount);
   }
}
