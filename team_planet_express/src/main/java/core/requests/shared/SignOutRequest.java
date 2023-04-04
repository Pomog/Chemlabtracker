package core.requests.shared;

import core.support.CurrentUserId;
import lombok.Value;

@Value
public class SignOutRequest {

    CurrentUserId userId;

}
