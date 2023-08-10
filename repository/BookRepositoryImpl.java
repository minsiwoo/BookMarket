package com.fastcampus.ch5.repository;

import com.fastcampus.ch5.domain.Book;
import com.fastcampus.ch5.exception.BookIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private JdbcTemplate template;

    @Autowired
    public void setJdbctemplate(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private List<Book> listOfBooks = new ArrayList<Book>();

    public BookRepositoryImpl() {
        Book book1 = new Book("ISBN1234", "C# 교과서", 30000);
        book1.setAuthor("박용준");
        book1.setDescription(
                "기초부터 활용까지, C# 기본기를 탄탄하게 다진다! 20년 경력 전문가가 선별한 「핵심 내용」과 「학습 순서」로 구성! Visual Studio 2019 + C# 8.0, 최신 버전으로 배우자."
        );
        book1.setPublisher("길벗");
        book1.setCategory("IT전문서");
        book1.setUnitsInStock(1000);
        book1.setReleaseDate("2020/05/29");

        Book book2 = new Book("ISBN1235", "Node.js 교과서", 36000);
        book2.setAuthor("조현영");
        book2.setDescription(
                "이 책은 프런트부터 서버, 데이터베이스, 배포까지 아우르는 광범위한 내용을 다룬다. 군더더기 없는 직관적인 설명으로 기본 개념을 확실히 이해하고, 노드의 기능과 생태계를 사용해보면서 실제로 동작하는 서버를 만들어보자. 웹 서버, 웹 API 서버, 노드 서비스 테스트, SNS 서비스, 실시간 GIF 채팅방, 경매 시스템, 커맨드라인 인터페이스까지 경험할 수 있으며, 예제와 코드는 최신 문법을 사용해 실무에 바로 참고하거나 적용할 수 있게 했다."
        );
        book2.setPublisher("길벗");
        book2.setCategory("IT전문서");
        book2.setUnitsInStock(1000);
        book2.setReleaseDate("2020/07/25");

        Book book3 = new Book("ISBN1236", "어도비 XD CC 2020", 25000);
        book3.setAuthor("김두한");
        book3.setDescription(
                "사용자가 단 한 권으로, 쉽고 빠르게 어도비 XD를 배울 수 있도록 구성한 책이다. 어도비 XD는 모바일 환경과 다양한 콘텐츠의 발달로 UI/UX 디자인을 쉽고 빠르게 구현하고자 만들어진 프로그램으로, 〈어도비 XD CC 2020 무작정 따라하기〉는 UI/.UX 디자인 작업 전 미리 알아두어야 할 이론을 익히고, 기본 기능과 예제를 무작정 따라하여 기초를 탄탄히 쌓을 수 있으며, 실무 예제를 따라하여 어도비 XD를 제대로 활용할 수 있도록 도와준다."
        );
        book3.setPublisher("길벗");
        book3.setCategory("IT활용서");
        book3.setUnitsInStock(1000);
        book3.setReleaseDate("2019/05/29");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);

    }

    @Override
    public List<Book> getAllBookList() {
        String SQL = "SELECT * FROM book";
        List<Book> listOfBooks = template.query(SQL, new BookRowMapper());
        return listOfBooks;
    }

    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<Book>();
        String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
        booksByCategory = template.query(SQL, new BookRowMapper());
        return booksByCategory;
    }

    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByPublisher = new HashSet<Book>();
        Set<Book> booksByCategory = new HashSet<Book>();
        Set<String> criterias = filter.keySet();

        if (criterias.contains("publisher")) {
            for (int j = 0; j < filter.get("publisher").size(); j++) {
                String publisherName = filter.get("publisher").get(j);
                String SQL = "SELECT * FROM book where b_publisher LIKE '%" + publisherName + "%'";
                booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
            }
        }

        if (criterias.contains("category")) {
            for (int i = 0; i < filter.get("category").size(); i++) {
                String category = filter.get("category").get(i);
                String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
                booksByCategory.addAll(template.query(SQL, new BookRowMapper()));
            }
        }

        booksByCategory.retainAll(booksByPublisher);
        return booksByCategory;
    }

    public Book getBookById(String bookId) {
        Book bookInfo = null;
        String SQL = "SELECT count(*) FROM book where b_bookId=?";
        int rowCount = template.queryForObject(SQL, Integer.class, bookId);
        if (rowCount != 0) {
            SQL = "SELECT * FROM book where b_bookId=?";
            bookInfo = template.queryForObject(SQL, new Object[]{bookId}, new BookRowMapper());
        }

        if (bookInfo == null) throw new BookIdException(bookId);
        return bookInfo;

    }

    public void setNewBook(Book book) {
        String SQL = "INSERT INTO book (b_bookId, b_name, b_unitPrice, b_author, b_description, b_publisher, b_category, b_unitsInstock, b_releaseDate, b_condition, b_fileName)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        template.update(SQL, book.getBookId(), book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName());
    }

    public void setUpdateBook(Book book) {
        if(book.getFileName() != null) {
            String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ? , b_description = ?, b_publisher = ?, b_category = ? , b_unitsInStock =?, b_releaseDate = ?, b_condition =?, b_fileName = ?, where b_bookId = ?";
            template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName(), book.getBookId());
        } else if(book.getFileName() == null) {
            String SQL = "UPDATE Book SET b_name = ?, b_unitPrice = ?, b_author = ? , b_description = ?, b_publisher = ?, b_category = ? , b_unitsInStock =?, b_releaseDate = ?, b_condition =?, b_fileName = ?, where b_bookId = ?";
            template.update(SQL, book.getName(), book.getUnitPrice(), book.getAuthor(), book.getDescription(), book.getPublisher(), book.getCategory(), book.getUnitsInStock(), book.getReleaseDate(), book.getCondition(), book.getFileName(), book.getBookId());
        }
    }

    public void setDeleteBook(String bookID) {
        String SQL = "DELETE from Book where b_bookId = ?";
        this.template.update(SQL, bookID);
    }
}

