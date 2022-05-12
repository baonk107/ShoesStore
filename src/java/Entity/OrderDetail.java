package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class OrderDetail {

    private int OrderID, ProductID;
    private double UnitPrice;
    private int Quantity;
    private double Discount;
}
