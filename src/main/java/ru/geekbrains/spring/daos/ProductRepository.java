package ru.geekbrains.spring.daos;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.Product;

import java.util.List;

@Component
public interface ProductRepository {
    Product findProductById(Long id);
    List<Long> findAllProductsId();
}
