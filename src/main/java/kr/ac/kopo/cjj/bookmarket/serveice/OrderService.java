package kr.ac.kopo.cjj.bookmarket.serveice;

import kr.ac.kopo.cjj.bookmarket.domain.Order;

public interface OrderService {
    void confirmOrder(String bookId, long quantity);
    long saveOrder(Order order);
}
