package shop.acceptance.customer;

import org.junit.jupiter.api.Test;
import shop.acceptance.AcceptanceTest;
import shop.core.domain.user.UserRole;
import shop.core.responses.shared.SignInResponse;
import shop.core.responses.shared.SignOutResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SignInAndOutAcceptanceTest extends AcceptanceTest {

    @Test
    void shouldSignInAsCustomerAndBecomeGuestAfterSignOut() {
        addCustomer("Morbo", "theAnnihilator", "pathetichumans");
        SignInResponse signInResponse = executeSignIn("theAnnihilator", "pathetichumans");
        assertFalse(signInResponse.hasErrors());
        assertEquals(getCurrentUserId().getValue(), getDatabase().accessUserDatabase().findByLoginName("theAnnihilator").orElseThrow().getId());
        assertEquals(UserRole.CUSTOMER, signInResponse.getUser().getUserRole());
        assertEquals("Morbo", signInResponse.getUser().getName());
        SignOutResponse signOutResponse = executeSignOut();
        assertFalse(signOutResponse.hasErrors());
        assertEquals(UserRole.GUEST, getDatabase().accessUserDatabase().findById(getCurrentUserId().getValue()).orElseThrow().getUserRole());
    }

}
