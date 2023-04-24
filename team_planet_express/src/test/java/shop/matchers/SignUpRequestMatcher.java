package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.guest.SignUpRequest;
import shop.core.support.CurrentUserId;

public class SignUpRequestMatcher implements ArgumentMatcher<SignUpRequest> {

    private final CurrentUserId userId;
    private final String name;
    private final String loginName;
    private final String password;

    public SignUpRequestMatcher(CurrentUserId userId, String name, String loginName, String password) {
        this.userId = userId;
        this.name = name;
        this.loginName = loginName;
        this.password = password;
    }

    @Override
    public boolean matches(SignUpRequest request) {
        return userId.equals(request.getUserId()) &&
                name.equals(request.getName()) &&
                loginName.equals(request.getLoginName()) &&
                password.equals(request.getPassword());
    }

}
