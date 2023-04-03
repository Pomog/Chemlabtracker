package core.database;

import core.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InMemoryUserDatabaseImplTest {

    private final User mockUser = mock(User.class);

    private final InMemoryUserDatabaseImpl database = new InMemoryUserDatabaseImpl();

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockUser);
        assertEquals(1, database.getUsers().size());
    }

    @Test
    void shouldReturnCreatedUser() {
        User createdUser = database.save(mockUser);
        assertNotNull(createdUser);
    }

    @Test
    void shouldSetNextIdForUser() {
        Long idBefore = database.getNextId();
        database.save(mockUser);
        verify(mockUser).setId(idBefore);
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockUser);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

    @Test
    void shouldReturnFoundUserById() {
        when(mockUser.getId()).thenReturn(1L);
        database.getUsers().add(mockUser);
        assertTrue(database.findById(1L).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentUserById() {
        when(mockUser.getId()).thenReturn(2L);
        database.getUsers().add(mockUser);
        assertTrue(database.findById(1L).isEmpty());
    }

    @Test
    void shouldReturnFoundUserByLogin() {
        when(mockUser.getLogin()).thenReturn("user");
        database.getUsers().add(mockUser);
        assertTrue(database.findByLogin("user").isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNonexistentUserByLogin() {
        when(mockUser.getLogin()).thenReturn("different user");
        database.getUsers().add(mockUser);
        assertTrue(database.findByLogin("user").isEmpty());
    }

    @Test
    void shouldReturn3Users() {
        database.getUsers().add(mockUser);
        database.getUsers().add(mockUser);
        database.getUsers().add(mockUser);
        assertEquals(3, database.getAllUsers().size());
    }

}
