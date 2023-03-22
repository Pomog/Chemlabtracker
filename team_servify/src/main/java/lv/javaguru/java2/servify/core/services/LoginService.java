package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.UsersDatabase;

public class LoginService {
    private UsersDatabase userDB;

    public LoginService(UsersDatabase userDB) {
        this.userDB = userDB;
    }

    public boolean checkCredentials(String email, String password) {
        return true;
    }
}
