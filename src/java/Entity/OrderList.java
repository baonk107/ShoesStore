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

public class OrderList {
    private int OrderID;
    private String  customerName;//ContactName
    private String OrderDate;
    private String address;
    private double TotalPrice;
    private int status;
}
