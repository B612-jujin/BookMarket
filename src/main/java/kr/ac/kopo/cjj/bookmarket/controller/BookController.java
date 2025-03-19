package kr.ac.kopo.cjj.bookmarket.controller;

import com.fasterxml.jackson.databind.Module;
import kr.ac.kopo.cjj.bookmarket.domain.Book;
import kr.ac.kopo.cjj.bookmarket.serveice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService; // 서비스계층과 연결

    @RequestMapping(value = "/books",method = RequestMethod.GET) //페이지 주소가 호출되었을 때 발동!
    public String requstBooklist(Model model){
        List<Book> bookList = bookService.getAllBookList();
        model.addAttribute("bookList", bookList); // 모델에  bookList란  책 정보 전달
        return "books";
    }
}
