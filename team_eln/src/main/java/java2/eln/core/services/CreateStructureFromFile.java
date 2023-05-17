package java2.eln.core.services;

import java2.eln.core.domain.StructureData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateStructureFromFile {
    String filename = "team_eln/src/data/demoReaction1.txt";

    public CreateStructureFromFile(String filename) {
        this.filename = filename;
    }

    public StructureData readFromFile (String identifier) {
        StructureData structureData = new StructureData("C", "No name", 0.0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith(identifier)) { // only process lines that start identifier
                    String[] fields = line.split(": "); // split the line into identifier and structure data
                    double mass = Double.parseDouble(fields[1].split(", ")[2]); // extract mass
                    String smiles = fields[1].split(", ")[1]; // extract the SMILES string
                    String name = fields[1].split(", ")[0]; // extract the name
                    return new StructureData(smiles, name, mass);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
          }
        return structureData;
    }
}

