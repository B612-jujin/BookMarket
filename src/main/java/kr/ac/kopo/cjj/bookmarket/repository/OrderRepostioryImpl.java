package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class OrderRepostioryImpl implements OrderRepository{
    private Map<Long, Order> listOfOrders;
    private long NextOrderId;

    public OrderRepostioryImpl() {
        listOfOrders = new HashMap<Long, Order>();
        NextOrderId = 2000;
    }


    @Override
    public Long saveOrder(Order order) {
        order.setOrderId(getNextOrderId());
        listOfOrders.put(order.getOrderId(), order);
        return order.getOrderId();
    }

    private synchronized long getNextOrderId() {
        return NextOrderId++;
    }
}
