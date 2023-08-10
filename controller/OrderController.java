package com.fastcampus.ch5.controller;

import com.fastcampus.ch5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/ISBN1234/2")
    public String process() {
        orderService.confirmOrder("ISBN1234", 2);
        return "redirect:/books";
    }
}
