package kr.ac.kopo.cjj.bookmarket.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonException{
    @ExceptionHandler
    private ModelAndView handleException(BookIdException e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e.toString());
        mav.setViewName("errorCommon");
        System.out.println("클래스확인용");
        return mav;
    }
}
