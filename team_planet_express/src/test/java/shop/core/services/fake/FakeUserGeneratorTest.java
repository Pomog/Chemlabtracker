package shop.core.services.fake;

import org.junit.jupiter.api.Test;
import shop.core.domain.user.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeUserGeneratorTest {

    private final FakeUserGenerator userGenerator = new FakeUserGenerator();

    @Test
    void shouldCreateListOf4Users() {
        List<User> users = userGenerator.createUsers();
        assertEquals(4, users.size());
        assertEquals("Customer", users.get(1).getName());
    }

}
