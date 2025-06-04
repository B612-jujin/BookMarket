package kr.ac.kopo.cjj.bookmarket.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonException{
    @ExceptionHandler(value = CategoryException.class)
    private ModelAndView handleException(HttpServletRequest request, CategoryException e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e.toString());
        mav.addObject("category", e.getCategory());
        mav.addObject("url", request.getRequestURL());
        mav.addObject("errorMessage", e.getErrorMessage());
        mav.setViewName("errorCommon");
        return mav;
    }
}
