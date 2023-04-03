package core.services.user;

import core.domain.user.UserRole;

public record UserRecord(String name,
                         String loginName,
                         String password,
                         UserRole userRole) {

}
