package java2.fitness_app.users.core.domain;

public abstract class Subscription {

    private String name;
    private int price;

    public Subscription(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public abstract boolean hasTrial();

    public abstract boolean isFree();

    public abstract boolean isPremium();
}

