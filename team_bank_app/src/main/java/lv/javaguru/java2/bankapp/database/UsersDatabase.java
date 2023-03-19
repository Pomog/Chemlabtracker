package lv.javaguru.java2.bankapp.database;

import lv.javaguru.java2.bankapp.domain.User;

import java.util.List;

public interface UsersDatabase {

        void addUsers(User user);
        void deleteUsers(User user);
        List<User> getAllUsers();
    }

