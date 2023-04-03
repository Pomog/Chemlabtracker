package core.services.fake;

import core.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class FakeCartGeneratorTest {

    private final User mockUser = mock(User.class);

    private final FakeCartGenerator fakeCartGenerator = new FakeCartGenerator();

    @Test
    void shouldCreateListOf2Carts() {
        List<User> users = List.of(mockUser, mockUser);
        assertEquals(2, fakeCartGenerator.createCartsForUsers(users).size());
    }

}
