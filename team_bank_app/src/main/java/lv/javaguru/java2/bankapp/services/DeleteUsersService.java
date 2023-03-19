package lv.javaguru.java2.bankapp.services;

import lv.javaguru.java2.bankapp.domain.User;
import lv.javaguru.java2.bankapp.database.UsersDatabase;

public class DeleteUsersService {
    private UsersDatabase database;

    public DeleteUsersService(UsersDatabase database) {
        this.database = database;
    }

    public void deleteUsers(String newName, String newSurname, String newGender, int newAge){
        database.deleteUsers(new User(newName, newSurname, newGender, newAge));
    }
}
