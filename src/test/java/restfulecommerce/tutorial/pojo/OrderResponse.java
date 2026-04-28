package restfulecommerce.tutorial.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private String message;
    private List<Order> orders;
}
