import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {
    private Registration registration;

    @BeforeEach
    void setUp() {
        registration = new Registration();
    }

    @Test
    void testRegisterUser() {
        registration.registerUser("john", "password123");
        assertEquals(1, registration.getUserList().size());
    }

    @Test
    void testRegisterUserWithTakenUsername() {
        registration.registerUser("john", "password123");
        registration.registerUser("john", "password456");
        assertEquals(1, registration.getUserList().size());
    }

    @Test
    void testIsUsernameAvailable() {
        registration.registerUser("john", "password123");
        assertFalse(registration.isUsernameAvailable("john"));
        assertTrue(registration.isUsernameAvailable("jane"));
    }
}