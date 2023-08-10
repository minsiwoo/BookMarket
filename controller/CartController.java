package com.fastcampus.ch5.controller;

import com.fastcampus.ch5.domain.Book;
import com.fastcampus.ch5.domain.Cart;
import com.fastcampus.ch5.domain.CartItem;
import com.fastcampus.ch5.exception.BookIdException;
import com.fastcampus.ch5.service.BookService;
import com.fastcampus.ch5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String requestCartId(HttpServletRequest request) {
        String sessionid = request.getSession(true).getId();
        return "redirect:/cart/" + sessionid;
    }

    @PostMapping
    public @ResponseBody Cart create(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @GetMapping("/{cartId}")
    public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
        Cart cart = cartService.read(cartId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PutMapping("/{cartId}")
    public @ResponseBody Cart read(@PathVariable(value ="cartId") String cartId) {
        return cartService.read(cartId);
    }

    @PutMapping("/add/{bookId}")
    @ResponseStatus(value= HttpStatus.NO_CONTENT)
    public void addCartByNewItem(@PathVariable String bookId, HttpServletRequest request) {
        // 장바구니 세션ID 가져오기
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);
        if(cart == null)
            cart = cartService.create(new Cart(sessionId));
        // 경로 변수 bookId에 대한 정보 얻어 오기
        Book book = bookService.getBookById(bookId);
        if(book == null)
            throw new IllegalArgumentException(new BookIdException(bookId));
        // bookId에 대한 도서 정보를 장바구니에 등록
        cart.addCartItem(new CartItem(book));
        cartService.update(sessionId, cart);  // 장바구니 갱신

    }

    @PutMapping("/remove/{bookId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartByItem(@PathVariable String bookId, HttpServletRequest request) {
        // 장바구니 세션 ID 가져오기
        String sessionId = request.getSession(true).getId();
        Cart cart = cartService.read(sessionId);
        if(cart==null)
            cart = cartService.create(new Cart(sessionId));
            // 경로 변수 bookId에 대한 정보 얻어오기
        Book book = bookService.getBookById(bookId);
        if(book==null)
            throw new IllegalArgumentException(new BookIdException(bookId));
        // bookId에 도서 정보 장바구니에서 삭제
        cart.removeCartItem(new CartItem(book));
        cartService.update(sessionId, cart);  // 세션 갱신
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCartList(@PathVariable(value="cartId") String cartId) {
        cartService.delete(cartId);
    }

}
