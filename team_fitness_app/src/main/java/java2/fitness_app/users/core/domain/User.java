package java2.fitness_app.users.core.domain;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private Long id;
    private final String username;
    private final String password;

    LocalDate endOfSubscriptionDate;
    Subscription subscription;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.subscription = subscription.TRAIL;
        this.endOfSubscriptionDate = LocalDate.now().plusDays(30);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return "users.User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public boolean hasAccessToPremiumFeatures() {
        if (subscription == Subscription.PREMIUM) {
            return true;
        } else {
            return false;
        }
    }
}
