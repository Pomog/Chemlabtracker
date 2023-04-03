package core.requests.shared;

import core.support.MutableLong;
import lombok.Value;

@Value
public class SignInRequest {

    MutableLong userId;
    String loginName;
    String password;

}
