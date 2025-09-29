package kr.ac.kopo.cjj.bookmarket.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_order_id")
    private Map<String, OrderItem> orderItems = new HashMap<String, OrderItem>();

    private BigDecimal grandTotal;


}
