package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MT Bac Ninh
 */
@Builder
@Getter
@Setter

public class Category {
    private int cateID;
    private String cateName;
    private String des;
}
