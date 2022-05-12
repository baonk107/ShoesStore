package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Employee {

    private int EmployeeID;//auto number
    private String LastName,
            FirstName,
            Title,
            TitleOfCourtesy,
            BirthDate,
            HireDate,
            Address,
            City,
            Region,
            PostalCode,
            Country,
            HomePhone,
            Extension,
            Photo,
            Notes;
    private int ReportsTo;
    private String PhotoPath;
    private String userName;
    private String passWord;
    private int roll;
}
