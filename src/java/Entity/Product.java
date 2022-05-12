package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author MT Bac Ninh
 */
@Builder
@Getter
@Setter
@ToString

public class Product {
    private int ProductID;//auto number
    private String pName;
    private int SupplierID, CategoryID;
    private String QuantityPerUnit;
    private double UnitPrice;
    private int UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued;
    private String imageURL;
    private String desPro;
    private double discount;
    
}
