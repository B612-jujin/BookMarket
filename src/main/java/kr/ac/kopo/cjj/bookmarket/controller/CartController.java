package kr.ac.kopo.cjj.bookmarket.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.cjj.bookmarket.domain.Cart;
import kr.ac.kopo.cjj.bookmarket.serveice.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;


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

}
