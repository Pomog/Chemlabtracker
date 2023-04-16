package java2.fitness_app.users.domain;

public class PremiumSubscription extends Subscription {
    private boolean autoRenew;

    public PremiumSubscription(String name, int price, boolean autoRenew) {
        super(name, price);
        this.autoRenew = autoRenew;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }
    @Override
    public boolean hasTrial() {
        return false;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isPremium() {
        return true;
    }
}
