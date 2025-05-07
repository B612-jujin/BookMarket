package kr.ac.kopo.cjj.bookmarket.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
//공백 두지 말고 ㅇㅇ 클래스 바로 위에 적어두자
@Data
//@Getter @Setter @NoArgsConstructor
public class Book {

    private String bookId; //도서번호
    private String name; //도서명
    private BigDecimal unitprice; //단가
    private String author; //저자
    private String description; // 도서설명
    private String publisher; // 출판사
    private String category; // 도서분류
    private long unitsInStock; // 재고량
    private String releaseDate; // 출판일
    private String condition; // 신규도서 or 중고도서 or 전자책
    private String fileName; // 도서 이미지 파일
    private MultipartFile bookImage; // 업로드된 도서 이미지 파일



}
