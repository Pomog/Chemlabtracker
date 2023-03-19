package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.UsersDatabase;
import lv.javaguru.java2.servify.domain.UserEntity;

public class AddUserService {
    private UsersDatabase usersDatabase;

    public AddUserService(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public void act(String firstName, String secondName, String nickName, String email, String phoneNumber) {
        UserEntity user = new UserEntity(firstName, secondName, nickName, email, phoneNumber);
        usersDatabase.add(user);
    }
}
