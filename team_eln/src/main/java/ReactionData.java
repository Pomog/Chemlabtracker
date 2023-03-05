import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReactionData {
    private final String code;
    private final String name;
    private List<StructureData> startingMaterials;

    private ConditionData conditions;
    private List<StructureData> products;
    private StructureData mainProduct;
    private List<File> analyticalResults;

    public ReactionData(String code, String name) {
        this.code = code;
        this.name = name;
        this.startingMaterials = new ArrayList<StructureData>();
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void addStartingMaterial (StructureData material){
        startingMaterials.add(material);
    }

    public void addProduct (StructureData material){
        products.add(material);
    }

    public void setMainProduct(StructureData mainProduct) {
        this.mainProduct = mainProduct;
    }

    public List<StructureData> getStartingMaterials() {
        return startingMaterials;
    }

    public void setConditions(ConditionData conditions) {
        this.conditions = conditions;
    }
    public ConditionData getConditions() {
        return conditions;
    }

    public List<StructureData> getProducts() {
        return products;
    }

    public StructureData getMainProduct() {
        return mainProduct;
    }

    @Override
    public String toString() {
        return "*** ReactionData{" +
                "\n code='" + code + '\'' +
                "\n name='" + name + '\'' +
                "\n startingMaterials=" + startingMaterials +
                "\n products=" + products +
                "\n mainProduct=" + mainProduct +
                "\n Conditions: \n" + conditions +
                "} ***";
    }
}
