package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.UsersDatabase;
import lv.javaguru.java2.servify.domain.UserEntity;

public class AddUserService {
    private UsersDatabase usersDatabase;

    public AddUserService(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public void execute(String firstName, String secondName,  String email, String phoneNumber) {
        UserEntity user = new UserEntity(firstName, secondName, email, phoneNumber);
        usersDatabase.add(user);
    }
}
