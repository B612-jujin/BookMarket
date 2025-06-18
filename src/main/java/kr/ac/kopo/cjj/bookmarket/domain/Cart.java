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

    public void addCartItem(CartItem item) {
        String bookId = item.getBook().getBookId();
        if (cartItems.containsKey(bookId)) {
            CartItem cartItem = cartItems.get(bookId);
            cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
            cartItems.put(bookId, cartItem);
        } else {
            cartItems.put(bookId, item);
        }
       updateGrandTotal();
    }

    // 전체 주문총액을 업데이트하는 메소드
    private void updateGrandTotal() {
        grandTotal = new BigDecimal(0);
        for (CartItem item : cartItems.values()) {
            grandTotal = grandTotal.add(item.getTotalprice());

        }
    }

    public void removeCartItem(CartItem item) {
        String bookId = item.getBook().getBookId();
        cartItems.remove(bookId);
        updateGrandTotal();

    }
}
