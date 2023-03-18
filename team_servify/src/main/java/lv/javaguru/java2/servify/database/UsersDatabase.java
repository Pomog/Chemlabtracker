package lv.javaguru.java2.servify.database;

import lv.javaguru.java2.servify.domain.UserEntity;

import java.util.List;

public interface UsersDatabase {
    void add(UserEntity user);
    void deleteById(Long userId);
    List<UserEntity> getAll();
}
