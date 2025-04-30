package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Book;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookRepository {
    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBookByCategory(String category);
    Set<Book> getBookByFilter(Map<String, List<String>> filter);
    void setNewBook(Book book);
}
