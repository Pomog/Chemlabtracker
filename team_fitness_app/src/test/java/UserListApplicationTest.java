import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserListApplicationTest {

    private UserListApplication userListApplication;

    @BeforeEach
    void setUp() {
        List<User> userList = new ArrayList<>();
        userListApplication = new UserListApplication();
    }


    @Test
    void shouldDeleteUser() {
        // TODO: please fix test before pushing it to GitHub repo

        //userListApplication.registerUser((List) new User("John", "password123"));
        //userListApplication.registerUser((List) new User("Anna", "password789"));
        //userListApplication.deleteUser((List) new User("John", "password123"));
        //List<User> expected = List.of(new User("Anna", "password789"));
        //assertEquals(1, userListApplication.getUserList().size());
    }
}