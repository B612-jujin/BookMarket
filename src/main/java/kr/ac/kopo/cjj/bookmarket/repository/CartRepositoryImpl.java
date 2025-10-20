package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class CartRepositoryImpl implements CartPepository{
    private Map<String, Cart> listOfCarts;

    public CartRepositoryImpl() {
        this.listOfCarts = new HashMap<String, Cart>();
    }

    @Override
    public Cart create(Cart cart) {
        if (listOfCarts.containsKey(cart.getCartId())) {
            throw new IllegalArgumentException("Cart with ID " + cart.getCartId() + " already exists.");
        }
        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }


    @Override
    public void update(String cartId, Cart cart) {
        if(!listOfCarts.containsKey(cartId)) {
            throw new IllegalArgumentException("장바구니 목록을 갱신할 수 없습니다. 장바구니가 존재하지 않습니다.");
        }
        listOfCarts.put(cartId, cart);

    }

    @Override
    public Cart read(String cartId) {

        return listOfCarts.get(cartId);
    }

    @Override
    public void delete(String cartId) {
        if(!listOfCarts.containsKey(cartId)) {
            throw new IllegalArgumentException("장바구니 목록을 갱신할 수 없습니다. 장바구니가 존재하지 않습니다.");
        }
        listOfCarts.remove(cartId);
    }
}
