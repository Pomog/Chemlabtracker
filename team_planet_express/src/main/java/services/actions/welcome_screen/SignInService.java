package services.actions.welcome_screen;

import database.Database;
import domain.user.User;
import services.exception.WrongLoginName;
import services.exception.WrongLoginPassword;

import java.util.Optional;

public class SignInService {

    private static final String ERROR_WRONG_PASSWORD = "Error: wrong password.";
    private static final String ERROR_WRONG_NAME = "Error: wrong name.";

    private final Database database;
    private final User user;

    public SignInService(Database database, User user) {
        this.database = database;
        this.user = user;
    }

    public void execute(String name, String password) {
        Optional<User> currentUser = database.accessUserDatabase().findByLogin(name);
        if (currentUser.isEmpty()) {
            throw new WrongLoginName(ERROR_WRONG_NAME);
        }
        if (!currentUser.get().getPassword().equals(password)) {
            throw new WrongLoginPassword(ERROR_WRONG_PASSWORD);
        }
        user.setId(currentUser.get().getId());
    }

}
