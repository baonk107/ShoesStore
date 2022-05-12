package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Account {
    private String customerID;
    private String userName;
    private String passWord;
    private int roll; 
}
