package java2.eln.core.requests;

import java2.eln.domain.StructureData;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FindReactionRequest {

    String code;
    String name;
    StructureData startingMaterial;
    double yield;

    public FindReactionRequest(String code, String name, StructureData startingMaterial, double yield) {
        this.code = code;
        this.name = name;
        this.startingMaterial = startingMaterial;
        this.yield = yield;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public StructureData getStartingMaterial() {
        return startingMaterial;
    }

    public double getYield() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        return Double.parseDouble(df.format(yield));
    }
}
