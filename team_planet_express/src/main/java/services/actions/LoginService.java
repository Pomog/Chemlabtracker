package services.actions;

import database.Database;
import domain.user.CurrentUser;
import domain.user.User;
import services.exception.WrongLoginName;
import services.exception.WrongLoginPassword;

import java.util.Optional;

public class LoginService {
    private static final String ERROR_WRONG_PASSWORD = "Error: wrong password.";
    private static final String ERROR_WRONG_NAME = "Error: wrong name.";

    private final Database database;
    private final CurrentUser currentUser;

    public LoginService(Database database, CurrentUser currentUser) {
        this.database = database;
        this.currentUser = currentUser;
    }

    public void execute(String name, String password) {
        Optional<User> user = database.accessUserDatabase().findByName(name);
        if (user.isEmpty()) {
            throw new WrongLoginName(ERROR_WRONG_NAME);
        }
        if (!user.get().getPassword().equals(password)) {
            throw new WrongLoginPassword(ERROR_WRONG_PASSWORD);
        }
        currentUser.setUserId(user.get().getId());
    }

}