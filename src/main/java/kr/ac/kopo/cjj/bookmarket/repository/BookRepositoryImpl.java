package kr.ac.kopo.cjj.bookmarket.repository;

import kr.ac.kopo.cjj.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

//표시하는 어노테이션이다. 꼭 추가하자
@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();


        book1.setBookId("isbn:0001");
        book1.setName("스프링 부트 완전정복");
        book1.setUnitprice(BigDecimal.valueOf(35000));
        book1.setAuthor("송미영");
        book1.setDescription("스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구이다. 이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있다.");
        book1.setPublisher("길벗캠퍼스");
        book1.setCategory("컴퓨터공학");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2024년 12월 31일");
        book1.setCondition("신규도서");

        book2.setBookId("isbn:0002");
        book2.setName("꿈꾸는 책들의 도시");
        book2.setUnitprice(BigDecimal.valueOf(19800));
        book2.setAuthor("발터 뫼어스");
        book2.setDescription("인간이 빚어낼 수 있는 최고의 서사!《차모니아 대륙》에 발을 들어놓는 순간, 당신의 상상력은 무(無)처럼 증발하고 만다. 그리하여, “여기서부터 이야기는 시작된다.");
        book2.setPublisher("들녘");
        book2.setCategory("소설");
        book2.setUnitsInStock(1000);
        book2.setReleaseDate("2014년 08월 04일");
        book2.setCondition("중고도서");

        book3.setBookId("isbn:0003");
        book3.setName("어린왕자");
        book3.setUnitprice(BigDecimal.valueOf(10620));
        book3.setAuthor("앙투안 드 생텍쥐페리");
        book3.setDescription("다른 별에서 온 어린 왕자의 순수한 시선으로 모순된 어른들의 세계를 비추는 이 소설은, 꾸밈없는 진솔한 문체와 동화처럼 단순해 보이는 이야기 속에 삶을 돌아보는 깊은 성찰을 아름다운 은유로 녹여 낸 작품이다.");
        book3.setPublisher("열린책들");
        book3.setCategory("소설");
        book3.setUnitsInStock(1000);
        book3.setReleaseDate(" 2015년 10월 20일");
        book3.setCondition("중고도서");

        book4.setBookId("isbn:0004");
        book4.setName("전지적 독자 시점");
        book4.setUnitprice(BigDecimal.valueOf(12150));
        book4.setAuthor("싱숑");
        book4.setDescription("웹소설 읽기가 취미인 회사원 김독자. 퇴근길 지하철에 오른 그의 앞에 10년 동안 혼자 읽어온 웹소설 ‘멸살법’이 현실이 되어 펼쳐진다. 모두 혼돈과 공포에 빠져 있지만, 김독자 한 명만은 그 세상의 결말을 알고 있다.");
        book4.setPublisher("비채");
        book4.setCategory("소설");
        book4.setUnitsInStock(9158);
        book4.setReleaseDate("2020년 01월 20일");
        book4.setCondition("웹소설");



        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        listOfBooks.add(book4);

    }

    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }




    @Override
    public Book getBookById(String bookId) {
        Book bookInfo = null;

        for (Book book : listOfBooks) {
            if (!book.getBookId().isEmpty() && book != null && book.getBookId().equals(bookId)) {
                bookInfo = book;
                break;
            }
        }
        if (bookInfo == null) {
            throw new IllegalArgumentException("도서번호가" + bookId+"인 해당 도서를 찾을 수 없습니다.");
        }

        return bookInfo;
    }

    @Override
    public List<Book> getBookByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<>();

        // 카테고리 중복 제거를 위한 리스트
        for (Book book : listOfBooks) {
            if (book.getCategory() != null && book.getCategory().equals(category)) {
                booksByCategory.add(book);
            }
/*            else {
                throw new IllegalArgumentException("해당 카테고리의 도서를 찾을 수 없습니다.");
            }*/
        }
        if (booksByCategory.isEmpty()) {
            throw new IllegalArgumentException("해당 카테고리의 도서를 찾을 수 없습니다.");
        }
        return booksByCategory;
    }

    @Override
    public Set<Book> getBookByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<String> booksByFilter = filter.keySet();

        if(booksByFilter.contains("publisher")) {
            for (int i =0; i < filter.get("publisher").size(); i++) {
                String publisherName = filter.get("publisher").get(i);
                for (Book book : listOfBooks) {
                    if(publisherName.equals(book.getPublisher())) {
                        booksByPublisher.add(book);
                    }
                }
            }
        if(booksByFilter.contains("category")) {
            for (int i = 0; i < filter.get("category").size(); i++) {
                String categoryName = filter.get("category").get(i);
                //구지 리스트여야 하나?
                List<Book> list = getBookByCategory(categoryName);
                booksByCategory.addAll(list);
                }
            }
        }

        // 교집합을 구하기 위해 booksByCategory와 booksByPublisher를 비교
        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    @Override
    public void setNewBook(Book book) {
        listOfBooks.add(book);
    }


}
