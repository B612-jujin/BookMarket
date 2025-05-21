package kr.ac.kopo.cjj.bookmarket.validator;


import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//사용자가 직접 만드는 어노테이션! -> 유효성 검사편
@Constraint(validatedBy = BookIdValidator.class) //어노테이션을 붙일 클래스
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME) //어노테이션이 언제까지 유지될 것인지
public @interface BookId {
    String message() default "{BookId.book.bookId}"; //유효성 검사 실패 시 출력할 메시지
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

}
