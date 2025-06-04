package kr.ac.kopo.cjj.bookmarket.exception;

import lombok.Getter;

@Getter
public class BookIdException extends RuntimeException {
    private String bookId;
    public BookIdException(String bookId) {
        super();
        this.bookId = bookId;
    }

}
