package kr.ac.kopo.cjj.bookmarket.serveice;

import kr.ac.kopo.cjj.bookmarket.domain.Cart;
import kr.ac.kopo.cjj.bookmarket.repository.CartPepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartPepository cartPepository;

    @Override
    public Cart create(Cart cart) {

        return cartPepository.create(cart);
    }

    @Override
    public Cart read(String cartId) {

        return cartPepository.read(cartId);
    }
}
