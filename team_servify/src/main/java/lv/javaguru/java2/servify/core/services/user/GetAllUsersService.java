package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.responses.user.GetAllUsersResponse;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public class GetAllUsersService {
    private UsersDatabase userDB;

    public GetAllUsersService(UsersDatabase userDB) {
        this.userDB = userDB;
    }

    public GetAllUsersResponse execute() {
        List<UserEntity> users = userDB.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
