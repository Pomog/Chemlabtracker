package shop.core.services.fake;

import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.ArrayList;
import java.util.List;

public class FakeUserGenerator {

    public List<User> createUsers() {
        List<User> fakeUsers = new ArrayList<>();
        fakeUsers.add(new User("Guest", "", "", UserRole.GUEST));
        fakeUsers.add(new User("Customer", "customer", "customer", UserRole.CUSTOMER));
        fakeUsers.add(new User("Manager", "manager", "manager", UserRole.MANAGER));
        fakeUsers.add(new User("Admin", "admin", "admin", UserRole.ADMIN));
        return fakeUsers;
    }

}
