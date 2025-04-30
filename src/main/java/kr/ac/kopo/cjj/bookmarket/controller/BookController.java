package kr.ac.kopo.cjj.bookmarket.controller;

import com.fasterxml.jackson.databind.Module;
import kr.ac.kopo.cjj.bookmarket.domain.Book;
import kr.ac.kopo.cjj.bookmarket.serveice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService; // 서비스계층과 연결

    @GetMapping
    public String requstBookList(Model model) {
        List<Book> bookList = bookService.getAllBookList();
        model.addAttribute("bookList", bookList); // 모델에  bookList란  책 정보 전달
        return "books";
    }


    @RequestMapping(value = "/all",method = RequestMethod.GET) //페이지 주소가 호출되었을 때 발동!
    public ModelAndView requstAllBooklist(){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("books");
        List<Book> bookList = bookService.getAllBookList();
        modelView.addObject("bookList", bookList); // 모델에  bookList란  책 정보 전달

        return modelView;
    }

    @GetMapping("/book")
    public String requstBookById(@RequestParam("id") String bookid, Model model) {
        Book book = bookService.getBookById(bookid);
        model.addAttribute("book", book);
        return "book";
    };

    @GetMapping("/{category}")
    public String requstBookByCategory(@PathVariable("category") String category, Model model) {
        List<Book> booksByCategory = bookService.getBookByCategory(category);
        model.addAttribute("bookList", booksByCategory);
        return "books";
    };

    @GetMapping("/filter/{bookFilter}")
    public String requstBookByFilter(@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter, Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";

    }

}
