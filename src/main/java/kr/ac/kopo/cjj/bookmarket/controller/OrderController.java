package kr.ac.kopo.cjj.bookmarket.controller;

import kr.ac.kopo.cjj.bookmarket.domain.*;
import kr.ac.kopo.cjj.bookmarket.serveice.BookService;
import kr.ac.kopo.cjj.bookmarket.serveice.CartService;
import kr.ac.kopo.cjj.bookmarket.serveice.OrderProService;
import kr.ac.kopo.cjj.bookmarket.serveice.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    Order order;
    List<Book> listofBooks;

    @Autowired
    private OrderProService orderProService;
    @Autowired
    private BookService bookService;

    //	@Autowired
    //   private BookService bookService;
    @GetMapping( "/{cartId}")
    public String requestCartList(@PathVariable(value = "cartId") String cartId, Model model) {
        Cart cart =  cartService.validateCart(cartId);
        order = new Order();
        listofBooks = new ArrayList<Book>();
        for(CartItem item : cart.getCartItems().values()){
            OrderItem  orderItem = new  OrderItem();
            Book book =item.getBook();
            listofBooks.add(book);
            orderItem.setBookId(book.getBookId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getTotalprice()); //setTotalPrice 이거였음
            order.getOrderItems().put(book.getBookId(), orderItem);
        }
        order.setCustomer(new Customer());
        order.setShipping(new Shipping());
        order.setGrandTotal(cart.getGrandTotal());
        return "redirect:/order/orderCustomerInfo";
    }
    @GetMapping( "/orderCustomerInfo")
    public String requestCustomerInfoForm(Model model) {
        //System.out.printf("order : %s\n 전달받은 정보입니다.", order);
        model.addAttribute("customer", order.getCustomer());
        return "orderCustomerInfo";
    }

    @PostMapping("/orderCustomerInfo")
    public String requestCustomerInfo( @ModelAttribute Customer  customer, Model model) {
        //System.out.printf("customer : %s\n 전달받은 정보입니다.", customer);
        order.setCustomer(customer);
        return "redirect:/order/orderShippingInfo";
    }

    @GetMapping("/orderShippingInfo")
    public String requestShippingInfoForm(Model model) {
        System.out.printf("sorder : %s\n 전달받은 정보입니다.", order);
        model.addAttribute("shipping", order.getShipping());
        return "orderShippingInfo";
    }

    @PostMapping("/orderShippingInfo")
    public String requestShippingInfo(@Valid @ModelAttribute Shipping  shipping, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "orderShippingInfo";
        order.setShipping(shipping);
        model.addAttribute("order",order);
        return "redirect:/order/orderConfirmation";
    }

    @GetMapping("/orderConfirmation")
    public String requestConfirmation( Model model ) {
        model.addAttribute("bookList",listofBooks);
        model.addAttribute("order",order);
        return "orderConfirmation";
    }
    @PostMapping("/orderConfirmation")
    public String requestConfirmationFinished( Model model ) {
        model.addAttribute("order",order);
        orderProService.save(order);
        return "redirect:/order/orderFinished";
    }

    @GetMapping("/orderFinished")
    public String requestFinished( HttpServletRequest request, Model model ) {
        // Long orderId=
        orderService.saveOrder(order);
        //order.setOrderId(orderId);
        model.addAttribute("order",order);
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "orderFinished";
    }

    @GetMapping("/orderCancelled")
    public String requestCancelled(HttpServletRequest request ) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "orderCancelled";
    }

    @GetMapping("/list")
    public String viewHomePage(Model model) {
        return viewPage(1, "orderId", "asc", model);
    }

    @GetMapping("/page")
    public String viewPage(@RequestParam("pageNum") int pageNum,@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir , Model model) {
        Page<Order> page = orderProService.listAll(pageNum, sortField, sortDir);
        List<Order> listOrders = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("orderList", listOrders);
        return "orderList";


    }

    @GetMapping("/view/{id}")
    public ModelAndView viewOrder(@PathVariable(value = "id") Long id) {
        Order order = orderProService.get(id);
        List<Book> listOfBooks = new ArrayList<Book>();
        for (OrderItem item : order.getOrderItems().values()) {
            String bookId = item.getBookId();
            Book book = bookService.getBookById(bookId);
            listOfBooks.add(book);
        }
        ModelAndView modelAndView = new ModelAndView("orderView");
        modelAndView.addObject("order", order);
        modelAndView.addObject("bookList", listOfBooks);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditOrder(@PathVariable(value = "id") Long id) {
        Order order = orderProService.get(id);
        List<Book> listOfBooks = new ArrayList<Book>();
        for (OrderItem orderItem : order.getOrderItems().values()) {
            String bookId = orderItem.getBookId();
            Book book = bookService.getBookById(bookId);
            listOfBooks.add(book);
        }

        ModelAndView modelAndView = new ModelAndView("orderEdit");
        modelAndView.addObject("order", order);
        modelAndView.addObject("bookList", listOfBooks);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable(value = "id") Long id) {
        orderProService.delete(id);
        return "redirect:/order/list";
    }

    @GetMapping("/deleteAll")
    public String deleteAllOrder() {
        orderProService.deleteAll();
        return "redirect:/order/list";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Order order){
        Order saveOrder = orderProService.get(order.getOrderId());
        saveOrder.setShipping(order.getShipping());
        orderProService.save(saveOrder);
        return "redirect:/order/list";
    }
}


