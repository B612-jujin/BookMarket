package kr.ac.kopo.cjj.bookmarket.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ToString
@Data
public class Cart {
    private String cartId;
    private Map<String , CartItem> cartItems;
    private BigDecimal grandTotal;


    public Cart() {
        cartItems = new HashMap<String , CartItem>();
        grandTotal = BigDecimal.ZERO; // 초기 총액은 0으로 설정
    }

    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }
}
