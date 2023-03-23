package lv.javaguru.java2.servify.core.requests;

public class AddUserRequest {

    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;

    public AddUserRequest(String firstName, String secondName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
