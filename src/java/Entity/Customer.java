package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Customer {

    private String CustomerID, CompanyName, ContactName,
            ContactTitle, Address, City, Region,
            PostalCode, Country, Phone, Fax, userName, passWord;
    private int roll;
}
