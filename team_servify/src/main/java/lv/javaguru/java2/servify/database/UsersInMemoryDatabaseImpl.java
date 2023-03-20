package lv.javaguru.java2.servify.database;

import lv.javaguru.java2.servify.domain.UserEntity;
import lv.javaguru.java2.servify.domain.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersInMemoryDatabaseImpl implements UsersDatabase {

    private long nextId = 10L;
    private List<UserEntity> usersDB = new ArrayList<>();

    @Override
    public void add(UserEntity user) {
        if(eMailValidation(user)) {
            user.setId(nextId);
            nextId++;
            user.setUserType(UserType.CUSTOMER);
            usersDB.add(user);
        }
    }

    private boolean eMailValidation(UserEntity user) {
        return !user.getEmail().isEmpty() && !user.getEmail().isBlank() && user.getEmail() != null;
    }

    private boolean credentialsValidation(String email, String password) {
        for (UserEntity user : usersDB) {
            if (user.getEmail().equals(email) && checkPassword(user.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }
    private boolean checkPassword(String passDB, String password) {
        return passDB.equals(password);
    }
    @Override
    public void deleteById(Long userId) {
        usersDB.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> user.setActive(false));
    }

    @Override
    public List<UserEntity> getAll() {
        return usersDB;
    }
}
