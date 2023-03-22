package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.UsersDatabase;

public class SetUserNotActiveService {
    private UsersDatabase usersDatabase;

    public SetUserNotActiveService(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    public void act(Long userId) {
        usersDatabase.setNotActiveByID(userId);
    }
}
