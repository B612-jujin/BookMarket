package kr.ac.kopo.cjj.bookmarket.serveice;

import kr.ac.kopo.cjj.bookmarket.domain.Book;
import kr.ac.kopo.cjj.bookmarket.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository; //퍼시스턴스 계층과 연결

    @Override
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }

    @Override
    public Book getBookById(String bookId) {
        Book book = bookRepository.getBookById(bookId);
        return book;
    }

    @Override
    public List<Book> getBookByCategory(String category) {
        List<Book> booksByCategory = bookRepository.getBookByCategory(category);
        return booksByCategory;
    }

}
