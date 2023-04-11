package java2.fitness_app.exercises.core.exercise_requests;

public class Ordering {

    private String orderBy;
    private String orderDirection;

    public Ordering(String orderBy, String orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

}
