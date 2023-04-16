package shop.core.requests.shared;

import lombok.Value;
import shop.core.support.CurrentUserId;

@Value
public class SignInRequest {

    CurrentUserId userId;
    String loginName;
    String password;

}
