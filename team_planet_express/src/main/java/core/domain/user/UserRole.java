package core.domain.user;

public enum UserRole {

    BANNED(0, "Banned"),
    GUEST(1, "Guest"),
    CUSTOMER(2, "Customer"),
    MANAGER(4, "Manager"),
    ADMIN(8, "Admin"),
    ALLUSERS(Integer.MAX_VALUE, "GOD");

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

    public boolean checkPermission(int permission) {
        return (permission & this.role) > 0;
    }

}
