package lv.javaguru.java2.bankapp.database;

import lv.javaguru.java2.bankapp.domain.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUsersDatabase implements UsersDatabase {

        private List<User> users = new ArrayList<>();

        @Override
        public void addUsers(User user) {
            users.add(user);
        }

        @Override
        public void deleteUsers(User user) {
            users.remove(user);
        }

        @Override
        public List<User> getAllUsers() {
            return users;
        }
    }

