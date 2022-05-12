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
public class CartProduct {

    private int proId;
    private String proName;
    private int quantity;
    private double price;
    private String urlImages;
    
}
