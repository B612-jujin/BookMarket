package kr.ac.kopo.cjj.bookmarket.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class CartItem {
    private Book book;
    private int quantity;
    private BigDecimal totalprice;


    public CartItem(Book book){
        this.book = book;
        this.quantity = 1; // 기본 수량은 1로 설정
        this.totalprice = book.getUnitprice(); // 총 가격은 책의 가격으로 초기화
    }

    public void setBook(Book book) {
        this.book = book;
        updateTotalPrice(); // 책 정보가 변경되면 총 가격도 업데이트
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotalPrice(); // 책 정보가 변경되면 총 가격도 업데이트
    }

    public void updateTotalPrice() {
        this.totalprice = book.getUnitprice().multiply(new BigDecimal (this.quantity));
    }
}
