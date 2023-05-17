package java2.eln.core.services;

import java2.eln.core.domain.StructureData;

public class GetStructureFromSMILE {
    private String smile;

    public GetStructureFromSMILE(String smile) {
        this.smile = smile;
    }
    public StructureData execute (){
        return new StructureData(smile);
    }
}
