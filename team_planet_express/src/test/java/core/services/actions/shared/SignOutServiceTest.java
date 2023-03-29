package core.services.actions.shared;

import core.database.Database;
import core.requests.shared.SignOutRequest;
import core.services.fake.FakeDatabaseInitializer;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SignOutServiceTest {

    private final Database fakeDatabase = new Database();
    private final SignOutRequest mockRequest = mock(SignOutRequest.class);

    private final SignOutService service = new SignOutService(fakeDatabase);
    private final MutableLong mockCurrentUserId = mock(MutableLong.class);

    @Test
    void shouldChangeUser() {
        new FakeDatabaseInitializer(fakeDatabase).initialize();
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        service.execute(mockRequest);
        // Fake database creates 4 users..
        verify(mockRequest.getUserId()).setValue(5L);
        //verify(mockCurrentUserId).setValue(1L);
    }

}
