package product;

import java.math.BigDecimal;

public interface ProductInterface {

     int getId();
     void setId(int id);
     String getName();
     void setName(String name);
     String getDescription();
     void setDescription(String description);
     BigDecimal getPrice();
     void setPrice(double price);
    String getCategory();
     void setCategory(String category);
}
