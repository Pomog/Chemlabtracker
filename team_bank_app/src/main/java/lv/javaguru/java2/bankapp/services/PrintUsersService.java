package lv.javaguru.java2.bankapp.services;

import lv.javaguru.java2.bankapp.domain.User;
import lv.javaguru.java2.bankapp.database.UsersDatabase;

import java.util.List;

public class PrintUsersService {
    private UsersDatabase database;

    public PrintUsersService(UsersDatabase database) {
        this.database = database;
    }
    public List<User> getAllUsers(){
        return database.getAllUsers();

    }
}
