package lv.javaguru.java2.bankapp.bank_service;

import lv.javaguru.java2.bankapp.domain.Bank;

public class PrintBalanceService {
    public void printBalance(Bank bank){
        System.out.println("Current balance statement");
        bank.printBalanceStatement();
    }
}
