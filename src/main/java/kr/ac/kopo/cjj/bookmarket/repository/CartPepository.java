package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Cart;

public interface CartPepository {
    Cart create(Cart cart);
    Cart read(String cartId);

    void update(String cartid, Cart cart);
    void delete(String cartId);// 전체 카트 삭제

}
