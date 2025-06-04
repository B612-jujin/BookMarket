package kr.ac.kopo.cjj.bookmarket.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// 카테고리를 찾을 수 없을 때 처리하기 위한 사용자 정의 예외클래스
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryException extends RuntimeException {
    private String errorMessage;
    private String Category;
    public CategoryException(String Category) {
        super();
        errorMessage = "요청한 도서 분야를 찾을 수 없습니다.";
        this.Category = Category;
        System.out.println(errorMessage);
    }

}
