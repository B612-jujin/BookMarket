package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Cart;

public interface CartPepository {
    Cart create(Cart cart);
    Cart read(String cartId);

}
