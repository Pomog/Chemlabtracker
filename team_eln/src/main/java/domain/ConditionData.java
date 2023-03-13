package domain;

import java.time.Duration;

public class ConditionData {
    private StructureData solvent;
    private int temperature;
    private String environment;
    private int pressure;
    private Duration reactionTime;

    public StructureData getSolvent() {
        return solvent;
    }

    public void setSolvent(StructureData solvent) {
        this.solvent = solvent;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public Duration getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Duration reactionTime) {
        this.reactionTime = reactionTime;
    }

    @Override
    public String toString() {
        double hours = (double)reactionTime.toMinutes() / 60;
        return "** baseClasses.ConditionData {" +
                "\n solvent=" + solvent +
                "\n temperature=" + temperature +
                "\n environment='" + environment + '\'' +
                "\n pressure=" + pressure +
                "\n reactionTime=" + String.format("%.2f", hours) + " h" +
                "}";
    }
}
