import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private int productID;
    private String name;
    private BigDecimal price;
    private String category;

    public Product(int productID, String name, BigDecimal price, String category) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getProductID(){return productID;}
    public int setProductID(int productID){return this.productID = productID;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) &&
               Objects.equals(name,product.name) &&
               Objects.equals(price,product.price) &&
               Objects.equals(category,product.category);
    }
    @Override
    public int hashCode() {
        return Objects.hash(productID,name,price,category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
