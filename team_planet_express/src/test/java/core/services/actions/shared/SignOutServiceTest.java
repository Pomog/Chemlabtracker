package core.services.actions.shared;

import core.database.Database;
import core.requests.shared.SignOutRequest;
import core.services.fake.FakeDatabaseInitializer;
import core.services.validators.actions.shared.SignOutValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SignOutServiceTest {

    private final Database fakeDatabase = new Database();
    private final SignOutValidator mockValidator = mock(SignOutValidator.class);
    private final SignOutRequest mockRequest = mock(SignOutRequest.class);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);

    private final SignOutService service = new SignOutService(fakeDatabase, mockValidator);

    @Test
    void shouldChangeUser() {
        new FakeDatabaseInitializer(fakeDatabase).initialize();
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        service.execute(mockRequest);
        verify(mockCurrentUserId).setValue(5L);
    }

}
