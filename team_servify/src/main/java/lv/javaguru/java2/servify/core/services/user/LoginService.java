package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

@DIComponent
public class LoginService {
    @DIDependency private UsersDatabase userDB;

    public boolean checkCredentials(String email, String password) {
        return true;
    }
}
