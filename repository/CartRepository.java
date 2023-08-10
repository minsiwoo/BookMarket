package com.fastcampus.ch5.repository;

import com.fastcampus.ch5.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);

    void update(String cartId, Cart cart);

    void delete(String cartId);
}
