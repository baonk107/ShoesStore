package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Order {
    private int OrderID; //auto number
    private String CustomerID;
    private int EmployeeID;
    private String OrderDate
      ,RequiredDate
      ,ShippedDate;
    private int ShipVia;
    private double Freight;
    private String ShipName
      ,ShipAddress
      ,ShipCity
      ,ShipRegion
      ,ShipPostalCode
      ,ShipCountry;
    private int status;
    private double totalPrice;
    
}
