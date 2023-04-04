package core.requests.shared;

import core.support.CurrentUserId;
import lombok.Value;

@Value
public class SignInRequest {

    CurrentUserId userId;
    String loginName;
    String password;

}
