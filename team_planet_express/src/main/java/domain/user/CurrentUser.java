package domain.user;

public class CurrentUser {

    private long userId;

    public CurrentUser(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
