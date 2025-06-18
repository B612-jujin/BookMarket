package kr.ac.kopo.cjj.bookmarket.serveice;


import kr.ac.kopo.cjj.bookmarket.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);

    void update(String cartId, Cart cart);
    void delete(String cartId); // 전체 카트 삭제
}
