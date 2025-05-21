package kr.ac.kopo.cjj.bookmarket.validator;

import kr.ac.kopo.cjj.bookmarket.domain.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UnitsInStockValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (book.getUnitprice()!=null && book.getUnitprice().intValue()>=10000 && book.getUnitsInStock()>=100) {
            errors.rejectValue("unitsInStock", "UnitsInStockValidator.message", "defult message");


        }


    }

}
