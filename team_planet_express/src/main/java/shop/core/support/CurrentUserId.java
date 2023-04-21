package shop.core.support;

import lombok.Data;
import shop.dependency_injection.DIComponent;

@Data
@DIComponent
public class CurrentUserId {

    private Long value;

}
