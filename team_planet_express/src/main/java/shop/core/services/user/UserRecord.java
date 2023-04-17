package shop.core.services.user;

import shop.core.domain.user.UserRole;

public record UserRecord(String name,
                         String loginName,
                         String password,
                         UserRole userRole) {

}
