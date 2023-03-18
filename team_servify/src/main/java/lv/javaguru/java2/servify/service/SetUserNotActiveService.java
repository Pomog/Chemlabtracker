package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.UsersDatabase;
import lv.javaguru.java2.servify.domain.UserEntity;

public class SetUserNotActiveService {
    private UsersDatabase usersDatabase;

    public SetUserNotActiveService(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public void act(Long userId) {
        usersDatabase.deleteById(userId);
    }
}
