package lv.javaguru.java2.bankapp.bank_service;

import lv.javaguru.java2.bankapp.domain.Bank;

public class UniqueCardService {
    public void uniqueCard(){
        System.out.println("Your unique card Number is :  ");
        Bank banks = new Bank();
        banks.BankNumber();
    }

}
