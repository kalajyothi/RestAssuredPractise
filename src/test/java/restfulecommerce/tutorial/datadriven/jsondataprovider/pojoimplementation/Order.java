package restfulecommerce.tutorial.datadriven.jsondataprovider.pojoimplementation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonPropertyOrder({"user_id", "product_id", "product_name", "product_amount", "qty", "tax_amt", "total_amt"})
public class Order {


    private String user_id;
    private String product_id;
    private String product_name;
    private double product_amount;
    private int qty;
    private double tax_amt;
    private double total_amt;
}
