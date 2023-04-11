package java2.fitness_app.exercises.search_engine;

import java2.fitness_app.exercises.core.exercise_domain.Exercise;

class NameSearchCriteria implements SearchCriteria{

    private final String name;

    NameSearchCriteria(String name) {
        this.name = name;
    }

    @Override
    public boolean match(Exercise exercise) {
        return name.equals(exercise.getName());
    }
}
