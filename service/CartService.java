package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
    Cart validateCart(String cartId);
}
