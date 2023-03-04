import java.io.File;
import java.util.List;

public class ReactionData {
    private final String code;
    private final String name;
    private List<StructureData> startingMaterials;
    private List<StructureData> products;
    private StructureData mainProduct;
    private List<File> analyticalResults;

    public ReactionData(String code, String name) {
        this.code = code;
        this.name = name;
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

    public List<StructureData> getProducts() {
        return products;
    }

    public StructureData getMainProduct() {
        return mainProduct;
    }
}
