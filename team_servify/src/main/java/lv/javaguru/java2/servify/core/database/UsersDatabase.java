package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public interface UsersDatabase {
    void add(UserEntity user);
    boolean deactivateUser(Long userId);
    List<UserEntity> getAllUsers();
}
