package kr.ac.kopo.cjj.bookmarket.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.ac.kopo.cjj.bookmarket.domain.Book;
import kr.ac.kopo.cjj.bookmarket.exception.BookIdException;
import kr.ac.kopo.cjj.bookmarket.exception.CategoryException;
import kr.ac.kopo.cjj.bookmarket.serveice.BookService;
import kr.ac.kopo.cjj.bookmarket.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService; // 서비스계층과 연결

//    @Autowired
//    private UnitsInStockValidator unitsInStockValidator; // Validator를 사용하기 위해서 Autowired로 주입받음
    @Autowired
    private BookValidator bookValidator;


    @Value("${file.uploadDir}")
    String fileDir; // 업로드할 파일의 경로를 가져옴

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
        if (booksByCategory == null || booksByCategory.isEmpty()) {
            throw new CategoryException(category);
        }
        model.addAttribute("bookList", booksByCategory);
        return "books";
    };

    @GetMapping("/filter/{bookFilter}")
    public String requstBookByFilter(@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter, Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";

    }

    @GetMapping("/add")
    public String requestAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String requestSubmitNewBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        // @Valid 어노테이션을 사용하여 Book 객체의 유효성 검사를 수행
        if(bindingResult.hasErrors()) {
            return "addBook";
        }
        MultipartFile bookImage = book.getBookImage();
        System.out.println("북 이미지 파일 이름 : " + bookImage.isEmpty());
        String saveName = bookImage.getOriginalFilename();
        File saveFile = new File(fileDir + saveName);
        if (bookImage != null && !bookImage.isEmpty()) {
            try {
                bookImage.transferTo(saveFile);
            } catch (IOException e) {
                throw new RuntimeException("도서 이미지 업로드가 되지 않았습니다.");
            }
        }
        book.setFileName(saveName);
        bookService.setNewBook(book);
        return "redirect:/books";


    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("addTitle", "신규 도서 등록");
    } // Model에 book이라는 객체를 추가하여, addBook.jsp에서 사용 가능하도록 함.

    @GetMapping("/download")//파일 다운로드 코드
    public void downloadBookImage(@RequestParam("file") String paramKey, HttpServletResponse response) throws IOException {
        File imageFile = new File(fileDir + paramKey);
        response.setContentType("application/download");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + paramKey + "\"");
        response.setContentLength((int) imageFile.length());
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(imageFile);
        FileCopyUtils.copy(fis,os);
        os.close();
        fis.close();
            };

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //binder.setValidator(unitsInStockValidator);
        binder.setValidator(bookValidator);
        binder.setAllowedFields("bookId","name","unitprice","author","description","publisher","category","unitsInStock","releaseDate","condition","bookImage");
    } // bookId는 사용자가 입력할 수 없도록 설정

    @ExceptionHandler(value = {BookIdException.class})
    public ModelAndView handleException(HttpServletRequest request, BookIdException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidBookId", e.getBookId());
        mav.addObject("exception", e.toString());
        mav.addObject("url", request.getRequestURL()+"?"+request.getQueryString());
        System.out.println("확인용");
        mav.setViewName("errorbook");
        return mav;
    }

}
