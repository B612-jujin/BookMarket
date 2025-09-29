package kr.ac.kopo.cjj.bookmarket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String country; // 국가
    private String zipcode; // 우편번호
    private String addressName; // 기본주소
    private String detailName; // 상세주소
}
