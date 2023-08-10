package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.Book;
import com.fastcampus.ch5.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }

    @Override
    public List<Book> getBookListByCategory(String category) {
        List<Book> booksByCategory = bookRepository.getBookListByCategory(category);
        return booksByCategory;
    }

    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
        return booksByFilter;
    }

    public Book getBookById(String bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        return bookById;
    }

    public void setNewBook(Book book) {
        bookRepository.setNewBook(book);
    }

    public void setUpdateBook(Book book) {
        bookRepository.setUpdateBook(book);
    }

    public void setDeleteBook(String bookID) {
        bookRepository.setDeleteBook(bookID);
    }
}
