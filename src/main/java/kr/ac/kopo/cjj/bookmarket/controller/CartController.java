package kr.ac.kopo.cjj.bookmarket.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.cjj.bookmarket.domain.Book;
import kr.ac.kopo.cjj.bookmarket.domain.Cart;
import kr.ac.kopo.cjj.bookmarket.domain.CartItem;
import kr.ac.kopo.cjj.bookmarket.exception.BookIdException;
import kr.ac.kopo.cjj.bookmarket.serveice.BookService;
import kr.ac.kopo.cjj.bookmarket.serveice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;


    @GetMapping
    public String requestCartId(HttpServletRequest request) {
        System.out.println("장바구니 요청");
        String sessionId = request.getSession().getId();
        return "redirect:/cart/" + sessionId;
    }

    @PostMapping
    public @ResponseBody Cart create(@RequestBody Cart cart) {
        System.out.printf("장바구니 생성 요청: %s\n", cart);
        return cartService.create(cart);
    }

    @GetMapping("/{cartId}")
    public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model) {
        System.out.printf("장바구니 조회 요청: %s\n", cartId);
        Cart cart = cartService.read(cartId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PutMapping("/{cartId}")
    public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId){
        System.out.printf("장바구니 조회 요청: %s\n", cartId);
        return cartService.read(cartId);

    }

    @PutMapping("/book/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addCartByNewItem(@PathVariable("bookId") String bookId, HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);

        if (cart == null) {
            cart = cartService.create(new Cart(sessionId));
        }
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException(new BookIdException(bookId));
        }

        cart.addCartItem(new CartItem(book));
        cartService.update(sessionId, cart);
    }

    @DeleteMapping("/book/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartByItem(@PathVariable("bookId") String bookId, HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);

        if (cart == null) {
            cart = cartService.create(new Cart(sessionId));
        }
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException(new BookIdException(bookId));
        }

        cart.removeCartItem(new CartItem(book));
        cartService.update(sessionId, cart);
    }

@DeleteMapping("/{cartId}")
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public void deleteCartList(@PathVariable(value = "cartId") String cartId) {
    System.out.printf("장바구니 삭제 요청: %s\n", cartId);
    cartService.delete(cartId);

    }

}
