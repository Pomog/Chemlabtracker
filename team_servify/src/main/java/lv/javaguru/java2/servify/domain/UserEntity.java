package lv.javaguru.java2.servify.domain;

import java.util.Objects;

public class UserEntity {

    private Long id;
    private String firstName;
    private String secondName;
    private String nickName;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private Address address;
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return isActive == that.isActive && id.equals(that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(nickName, that.nickName) && email.equals(that.email) && Objects.equals(phoneNumber, that.phoneNumber) && userType == that.userType && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, nickName, email, phoneNumber, userType, address, isActive);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                ", address=" + address +
                ", isActive=" + isActive +
                '}';
    }

    public UserEntity(String firstName, String secondName, String nickName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        isActive = true;
        this.userType = UserType.ANONYMOUS;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
