package kr.ac.kopo.cjj.bookmarket.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String CustomerId; // 고객ID
    private String name; // 이름
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address; // 주소
    private String phone; // 전화번호
}
