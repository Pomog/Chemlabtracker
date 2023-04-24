package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.responses.user.GetAllUsersResponse;
import lv.javaguru.java2.servify.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsersService {
    @Autowired private UsersDatabase userDB;

    public GetAllUsersResponse execute() {
        List<UserEntity> users = userDB.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
