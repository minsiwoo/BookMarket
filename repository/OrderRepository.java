package com.fastcampus.ch5.repository;

import com.fastcampus.ch5.domain.Order;

public interface OrderRepository {
    Long saveOrder(Order order);

}
