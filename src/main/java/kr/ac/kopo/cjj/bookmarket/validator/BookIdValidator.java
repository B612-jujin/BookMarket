package kr.ac.kopo.cjj.bookmarket.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.ac.kopo.cjj.bookmarket.domain.Book;
import kr.ac.kopo.cjj.bookmarket.serveice.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookIdValidator implements ConstraintValidator<BookId,String> {
    @Autowired
    private BookService bookService;

    @Override
    public void initialize(BookId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        // 초기화 작업이 필요하면 여기에 작성
    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Book book = null;
        try{
            book = bookService.getBookById(s);
        }catch (RuntimeException e){
            return true;
        }
        // bookId가
        if (book != null) {
            return false;
        }
        // bookId가 "isbn"으로 시작하는지 검사
        return true ;
    }
}
