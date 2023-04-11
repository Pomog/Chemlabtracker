package java2.fitness_app.exercises.core.exercise_domain;

import java.util.Objects;

public class Exercise {

    private final String name;
    private final MuscleGroup muscleGroup;
    private final Difficulty difficulty;
    private final Type type;
    private final Equipment equipment;

    Exercise(ExerciseBuilder builder) {
        this.name = builder.name;
        this.muscleGroup = builder.muscleGroup;
        this.difficulty = builder.difficulty;
        this.type = builder.type;
        this.equipment = builder.equipment;
    }

    public String getName() {
        return name;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Type getType() {
        return type;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) && muscleGroup == exercise.muscleGroup && difficulty == exercise.difficulty && type == exercise.type && equipment == exercise.equipment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, muscleGroup, difficulty, type, equipment);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", muscleGroup=" + muscleGroup +
                ", difficulty=" + difficulty +
                ", type=" + type +
                ", equipment=" + equipment +
                '}';
    }

    public static class ExerciseBuilder {

        private final String name;
        private final MuscleGroup muscleGroup;
        private Difficulty difficulty;
        private Type type;
        private Equipment equipment;

        public ExerciseBuilder(String name, MuscleGroup muscleGroup) {
            this.name = name;
            this.muscleGroup = muscleGroup;
        }

        public ExerciseBuilder Difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public ExerciseBuilder Type(Type type) {
            this.type = type;
            return this;
        }

        public ExerciseBuilder Equipment(Equipment equipment) {
            this.equipment = equipment;
            return this;
        }

        public Exercise build() {
            return new Exercise(this);
        }
    }
}
