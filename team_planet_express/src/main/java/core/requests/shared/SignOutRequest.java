package core.requests.shared;

import core.support.MutableLong;
import lombok.Value;

@Value
public class SignOutRequest {
    MutableLong userId;

}
