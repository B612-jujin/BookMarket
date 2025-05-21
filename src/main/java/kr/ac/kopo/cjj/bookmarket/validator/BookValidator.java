package kr.ac.kopo.cjj.bookmarket.validator;

import kr.ac.kopo.cjj.bookmarket.domain.Book;

import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;


@Component
public class BookValidator implements Validator {
    @Autowired
    private jakarta.validation.Validator beanValidator;

    public Set<Validator> springValidators;

    public BookValidator(){
        springValidators = new HashSet<Validator>();
    }

    public void setSpringValidators(Set<Validator> springValidators){
        this.springValidators = springValidators;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);
        for (ConstraintViolation<Object> violation : violations){
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }

        for (Validator validator : springValidators){
            validator.validate(target, errors);
        }
    }

}
