package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Book;
import java.util.List;

public interface BookRepository {
    List<Book> getAllBookList();

    Book getBookById(String bookId);
}
