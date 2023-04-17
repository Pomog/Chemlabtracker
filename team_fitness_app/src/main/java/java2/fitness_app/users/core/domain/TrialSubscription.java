package java2.fitness_app.users.core.domain;

import java2.fitness_app.users.core.domain.Subscription;

public class TrialSubscription extends Subscription {

    private int durationInDays;

    public TrialSubscription(String name, int price, int durationInDays) {
        super(name, price);
        this.durationInDays = durationInDays;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    @Override
    public boolean hasTrial() {
        return true;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isPremium() {
        return false;
    }
}
