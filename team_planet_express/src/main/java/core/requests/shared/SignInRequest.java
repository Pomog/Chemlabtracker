package core.requests.shared;

import core.support.MutableLong;
import lombok.Value;

@Value
public class SignInRequest {

    String loginName;
    String password;
    MutableLong userId;

}
