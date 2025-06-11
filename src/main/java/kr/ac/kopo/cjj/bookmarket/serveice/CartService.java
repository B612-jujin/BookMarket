package kr.ac.kopo.cjj.bookmarket.serveice;


import kr.ac.kopo.cjj.bookmarket.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
}
