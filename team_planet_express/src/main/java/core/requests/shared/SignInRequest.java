package core.requests.shared;

import lombok.Value;

@Value
public class SignInRequest {

    String loginName;
    String password;

}
