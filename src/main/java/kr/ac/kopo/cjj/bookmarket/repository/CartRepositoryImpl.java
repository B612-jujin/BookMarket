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
        return null;
    }

    @Override
    public Cart read(String cartId) {

        return null;
    }
}
