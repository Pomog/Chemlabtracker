package database;

import database.Database;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabaseImpl implements Database {

    private  Long nextId = 1L;

    public List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void registerNewUser(User user) {
        user.setId(nextId);
        nextId++;
        users.add(user);
    }

    @Override
    public boolean deleteUser(Long id, String password) {
        boolean userExists = users.stream()
                .anyMatch(user -> user.getId().equals(id) && user.getPassword().equals(password));
        if(userExists){
            users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .filter(user -> user.getPassword().equals(password))
                    .ifPresent(user -> users.remove(user));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean login(Long id, String password) {
        return users.stream()
                .anyMatch(user -> user.getId().equals(id) && user.getPassword().equals(password));
    }
}
