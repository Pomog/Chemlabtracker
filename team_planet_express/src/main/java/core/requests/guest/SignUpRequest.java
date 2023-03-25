package core.requests.guest;

import lombok.Value;

@Value
public class SignUpRequest {

    String name;
    String loginName;
    String password;

}
