package ru.geekbrains.spring.daos.impl;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.Product;
import ru.geekbrains.spring.daos.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component(value = "productRepository")
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bread", 26),
                new Product(2L, "Milk", 58),
                new Product(3L, "Apples", 99),
                new Product(4L, "Beans", 115),
                new Product(5L, "Eggs", 90)
        ));
    }

    @Override
    public Product findProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Long> findAllProductsId() {
        List<Long> idList = null;
        for (Product product : products) {
            idList.add(product.getId());
        }
        return idList;
    }
}
