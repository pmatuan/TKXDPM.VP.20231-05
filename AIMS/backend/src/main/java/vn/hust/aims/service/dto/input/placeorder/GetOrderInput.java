package vn.hust.aims.service.dto.input.placeorder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderInput {

    private String orderId;
}
