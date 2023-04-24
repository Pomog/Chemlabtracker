package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.shared.SignInRequest;
import shop.core.support.CurrentUserId;

public class SignInRequestMatcher implements ArgumentMatcher<SignInRequest> {

    private final CurrentUserId userId;
    private final String loginName;
    private final String password;

    public SignInRequestMatcher(CurrentUserId userId, String loginName, String password) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
    }

    @Override
    public boolean matches(SignInRequest request) {
        return userId.equals(request.getUserId()) &&
                loginName.equals(request.getLoginName()) &&
                password.equals(request.getPassword());
    }

}
