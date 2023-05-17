package java2.eln.core.services;

import java2.eln.core.domain.ConditionData;
import java2.eln.core.domain.StructureData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class CreateConditionDataFromFile {
    String filename = "team_eln/src/data/demoReaction1.txt";

    public CreateConditionDataFromFile(String filename) {
        this.filename = filename;
    }

    public ConditionData readFromFile () {
        String solventName = null, solventSMILES = null;
        int temperature = 20, pressure = 1;
        String environment = null;
        Duration reactionTime = null;
        double solventMass = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("Solvent:")) {
                    String[] fields = line.split(": "); // split the line into identifier and structure data
                    solventMass = Double.parseDouble(fields[1].split(", ")[2]); // extract the mass
                    solventSMILES = fields[1].split(", ")[1]; // extract the SMILES string
                    solventName = fields[1].split(", ")[0]; // extract the name
                } else if (line.startsWith("temperature:")) {
                    temperature = Integer.parseInt(line.split(": ")[1]);
                } else if (line.startsWith("pressure:")) {
                    pressure = Integer.parseInt(line.split(": ")[1]);
                } else if (line.startsWith("environment:")) {
                    environment = line.split(": ")[1];
                } else if (line.startsWith("reactionTime:")) {
                    String durationString = line.split(": ")[1];
                    reactionTime = Duration.parse(durationString);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        StructureData solvent = new StructureData(solventSMILES, solventName, solventMass);

        ConditionData conditions = new ConditionData();
        conditions.setSolvent(solvent);
        conditions.setTemperature(temperature);
        conditions.setEnvironment(environment);
        conditions.setPressure(pressure);
        conditions.setReactionTime(reactionTime);

        return conditions;
    }
}