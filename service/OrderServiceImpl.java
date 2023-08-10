package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.Book;
import com.fastcampus.ch5.domain.Order;
import com.fastcampus.ch5.repository.BookRepository;
import com.fastcampus.ch5.repository.CartRepository;
import com.fastcampus.ch5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

   @Autowired
    private BookRepository bookRepository;


    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public void confirmOrder(String bookId, long quantity) {
        Book bookById = bookRepository.getBookById(bookId);
        if(bookById.getUnitsInStock() < quantity) {
            throw new IllegalArgumentException("품절입니다. 사용가능한 제고수 :" + bookById.getUnitsInStock());
        }

        bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
    }

    public Long saveOrder(Order order) {
        Long orderId = orderRepository.saveOrder(order);
        cartService.delete(order.getCart().getCartId());
        return orderId;
    }
}
