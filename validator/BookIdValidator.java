package com.fastcampus.ch5.validator;

import com.fastcampus.ch5.exception.BookIdException;
import com.fastcampus.ch5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.fastcampus.ch5.domain.Book;

public class BookIdValidator implements ConstraintValidator<BookId, String>{
    @Autowired
    private BookService bookService;

    public void initialize(BookId constraintAnnotation) {

    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        Book book;
        try {
            book = bookService.getBookById(value);
        } catch(BookIdException e) {
            return true;
        }
        if(book!=null) {
            return false;
        }
        return true;
    }
}

