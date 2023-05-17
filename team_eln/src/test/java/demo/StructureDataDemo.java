package demo;

import java2.eln.core.domain.StructureData;

public class StructureDataDemo {
    public static void main(String[] args) {

        StructureData demoStructure = new StructureData(".", "Acetic acid", 1);
        System.out.println(demoStructure.getSmiles());
        System.out.println(demoStructure.getMol());
        System.out.println(demoStructure.getMW());
        System.out.println(demoStructure.getName());
    }
}
