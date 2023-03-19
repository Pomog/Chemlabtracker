package lv.javaguru.java2.bankapp.services;

import lv.javaguru.java2.bankapp.domain.User;
import lv.javaguru.java2.bankapp.database.UsersDatabase;

public class AddUsersService {
    private UsersDatabase database;

    public AddUsersService(UsersDatabase database) {
        this.database = database;
    }

    public void addUsers(String newName, String newSurname, String newGender, int newAge){
        User user = new User(newName, newSurname, newGender, newAge);
        database.addUsers(user);

    }
}
