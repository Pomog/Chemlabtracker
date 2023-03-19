package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.UsersDatabase;

public class LoginService {
    private UsersDatabase userDB;

    public LoginService(UsersDatabase userDB) {
        this.userDB = userDB;
    }

    public boolean checkUserAndPass(String email, char[] password) {
        return true;
    }
}
