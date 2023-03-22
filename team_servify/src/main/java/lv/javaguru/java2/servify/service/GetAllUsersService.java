package lv.javaguru.java2.servify.service;

import lv.javaguru.java2.servify.database.UsersDatabase;
import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public class GetAllUsersService {
    private UsersDatabase usersDB;

    public GetAllUsersService(UsersDatabase usersDB) {
        this.usersDB = usersDB;
    }

    public List<UserEntity> act() {
        return usersDB.getAll();
    }
}
