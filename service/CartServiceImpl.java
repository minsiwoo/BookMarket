package com.fastcampus.ch5.service;

import com.fastcampus.ch5.domain.Cart;
import com.fastcampus.ch5.exception.CartException;
import com.fastcampus.ch5.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart create(Cart cart) {
        return cartRepository.create(cart);
    }

    public Cart read(String cartId) {
        return cartRepository.read(cartId);
    }

    public void update(String cartId, Cart cart) {
        cartRepository.update(cartId, cart);
    }

    public void delete(String cartId) {
        cartRepository.delete(cartId);
    }

    public Cart validateCart(String cartId) {
        Cart cart = cartRepository.read(cartId);
        if(cart == null || cart.getCartItmes().size() == 0) {
            throw new CartException(cartId);
        }

        return cart;
    }
}
