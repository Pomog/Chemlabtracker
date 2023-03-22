package core.domain.user;

public enum UserRole {

    BANNED(0),
    GUEST(1),
    CUSTOMER(2),
    MANAGER(4),
    ADMIN(8),
    ALLUSERS(Integer.MAX_VALUE);

    private final int role;

    UserRole(int role) {
        this.role = role;
    }

    public int getRoleNumber() {
        return role;
    }

    public static int getAccessNumber(UserRole... roles) {
        int result = 0;
        for (UserRole role : roles) {
            result += role.getRoleNumber();
        }
        return result;
    }

    public boolean checkPermission(int permission) {
        return (permission & this.role) > 0;
    }

}
