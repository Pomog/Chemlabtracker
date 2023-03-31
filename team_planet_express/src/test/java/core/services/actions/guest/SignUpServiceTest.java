package core.services.actions.guest;

import core.database.Database;
import core.database.UserDatabase;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.requests.guest.SignUpRequest;
import core.responses.CoreError;
import core.responses.guest.SignUpResponse;
import core.services.validators.guest.SignUpValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class SignUpServiceTest {

    private final Database mockDatabase = mock(Database.class);
    private final SignUpValidator mockValidator = mock(SignUpValidator.class);
    private final SignUpRequest mockRequest = mock(SignUpRequest.class);
    private final CoreError mockCoreError = mock(CoreError.class);
    private final UserDatabase mockUserDatabase = mock(UserDatabase.class);
    private final User mockUser = mock(User.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);

    private final SignUpService service = new SignUpService(mockDatabase, mockValidator);

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        SignUpResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockUserDatabase.save(any(User.class))).thenReturn(mockUser);
        SignUpResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldSaveValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getName()).thenReturn("name");
        when(mockRequest.getLoginName()).thenReturn("login");
        when(mockRequest.getPassword()).thenReturn("password");
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.save(new User("name", "login", "password", UserRole.CUSTOMER)))
                .thenReturn(mockUser);
        service.execute(mockRequest);
        verify(mockUserDatabase)
                .save(new User("name", "login", "password", UserRole.CUSTOMER));
    }

    @Test
    void shouldUpdateCurrentUserId() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        when(mockDatabase.accessUserDatabase()).thenReturn(mockUserDatabase);
        when(mockUserDatabase.save(any(User.class))).thenReturn(mockUser);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(any(Long.class));
    }

}
