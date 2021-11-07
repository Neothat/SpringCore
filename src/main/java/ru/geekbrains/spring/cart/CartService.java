package ru.geekbrains.spring.cart;

import org.springframework.stereotype.Component;

@Component
public interface CartService {
    void addToCartById(Long id);
    void addToCartById(Long id, Integer amount);
    String getFinalCart();
    void addAllOneByOne();
    void emptyTrash();
}
