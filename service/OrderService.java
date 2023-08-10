package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.Order;

public interface OrderService {
    void confirmOrder(String bookId, long quantity);
    Long saveOrder(Order order);
}
