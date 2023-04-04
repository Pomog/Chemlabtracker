package core.requests.guest;

import core.support.CurrentUserId;
import lombok.Value;

@Value
public class SignUpRequest {

    CurrentUserId userId;
    String name;
    String loginName;
    String password;

}
