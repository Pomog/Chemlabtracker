package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.responses.user.GetAllUsersResponse;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

@DIComponent
public class GetAllUsersService {
    @DIDependency private UsersDatabase userDB;

    public GetAllUsersResponse execute() {
        List<UserEntity> users = userDB.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
