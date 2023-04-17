package java2.fitness_app.users.core.domain;

public class FreeSubscription  extends Subscription {

    public FreeSubscription(String name, int price) {
        super(name, price);
    }

    @Override
    public boolean hasTrial() {
        return false;
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public boolean isPremium() {
        return false;
    }
}
