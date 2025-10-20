package kr.ac.kopo.cjj.bookmarket.exception;

public class CartExcption extends RuntimeException{

    private String cartId;

    public CartExcption(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
}
