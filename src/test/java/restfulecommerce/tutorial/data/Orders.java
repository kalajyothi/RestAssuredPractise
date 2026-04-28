package restfulecommerce.tutorial.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"user_id", "product_id", "product_name", "product_amount", "qty", "tax_amt", "total_amt"})
public class Orders {

    private String user_id;
    private String product_id;
    private String product_name;
    private double product_amount;
    private int qty;
    private double tax_amt;
    private double total_amt;

    @Override
    public String toString() {
        return "Order{" + " user_id=" + user_id + " product_id=" + product_id + " product_name=" + product_name
                + " product_amount=" + product_amount + " qty=" + qty + " tax_amt=" + tax_amt + " tax_amt=" + total_amt;
    }

}
