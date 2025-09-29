package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Order;

public interface OrderRepository {
    //주문목록 저장
    Long saveOrder(Order order);
}
