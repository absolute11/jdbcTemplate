package ru.netelogy.springjdbc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/products/fetch-product")
    public String fetchProductName(@RequestParam String name) {
        return orderRepository.getProductName(name);
    }
}
