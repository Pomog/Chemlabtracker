package lv.javaguru.java2.servify.core.responses.user;

import lv.javaguru.java2.servify.core.responses.CoreResponse;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public class GetAllUsersResponse extends CoreResponse {

    private List<UserEntity> users;

    public GetAllUsersResponse(List<UserEntity> users) {
        this.users = users;
    }

    public List<UserEntity> getUsers() {
        return users;
    }
}
