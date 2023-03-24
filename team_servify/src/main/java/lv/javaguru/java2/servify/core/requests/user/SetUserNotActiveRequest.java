package lv.javaguru.java2.servify.core.requests.user;

public class SetUserNotActiveRequest {

    private Long userIdToSetInactive;

    public SetUserNotActiveRequest(Long userIdToSetInactive) {
        this.userIdToSetInactive = userIdToSetInactive;
    }

    public Long getUserIdToSetInactive() {
        return userIdToSetInactive;
    }
}
