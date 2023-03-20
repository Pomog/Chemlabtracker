package services.fake;

import domain.user.User;
import domain.user.UserRole;

import java.util.ArrayList;
import java.util.List;

public class FakeUserGenerator {

    public List<User> createUsers() {
        List<User> fakeUsers = new ArrayList<>();
        fakeUsers.add(new User("guest", "", "", UserRole.GUEST));
        fakeUsers.add(new User("customer", "customer", "customer", UserRole.CUSTOMER));
        fakeUsers.add(new User("manager", "manager", "manager", UserRole.MANAGER));
        fakeUsers.add(new User("admin", "admin", "admin", UserRole.ADMIN));
        return fakeUsers;
    }

}
