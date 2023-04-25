package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.UserEntity;
import lv.javaguru.java2.servify.domain.UserType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersInMemoryDatabaseImpl implements UsersDatabase {

    private long nextId = 0L;
    private List<UserEntity> usersDB = new ArrayList<>();

    @Override
    public void add(UserEntity user) {
        user.setId(nextId);
        nextId++;
        user.setUserType(UserType.CUSTOMER);
        usersDB.add(user);
    }

    private boolean validateCredentials(String email, String password) {
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
    public boolean deactivateUser(Long userId) {
        boolean userDeactivated = false;
        usersDB.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> user.setInactive(true));

        Optional<UserEntity> userToDeactivateOpt = usersDB.stream()
                .filter(userEntity -> userEntity.getId().equals(userId))
                .findFirst();

        if (userToDeactivateOpt.isPresent()) {
            UserEntity userToInactivate = userToDeactivateOpt.get();
            userDeactivated = userToInactivate.isInactive();
        }

        return userDeactivated;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return usersDB;
    }
}
