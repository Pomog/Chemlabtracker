package shop.core.domain.user;

public enum UserRole {

    GUEST(1, "Guest"),
    CUSTOMER(2, "Customer"),
    MANAGER(4, "Manager"),
    ADMIN(8, "Admin"),
    ALL_USERS(Integer.MAX_VALUE, "GOD");

    private final int role;
    private final String defaultName;

    UserRole(int role, String defaultName) {
        this.role = role;
        this.defaultName = defaultName;
    }

    public int getRoleNumber() {
        return role;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public static int getAccessNumber(UserRole... roles) {
        int result = 0;
        for (UserRole role : roles) {
            result += role.getRoleNumber();
        }
        return result;
    }

    public static int getAccessNumberExclude(UserRole... roles) {
        int result = UserRole.ALL_USERS.getRoleNumber();
        for (UserRole role : roles) {
            result -= role.getRoleNumber();
        }
        return result;
    }

    public boolean checkPermission(int permission) {
        return (permission & this.role) > 0;
    }

}
