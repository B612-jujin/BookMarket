package kr.ac.kopo.cjj.bookmarket.serveice;

import kr.ac.kopo.cjj.bookmarket.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBookByCategory(String category);


}
