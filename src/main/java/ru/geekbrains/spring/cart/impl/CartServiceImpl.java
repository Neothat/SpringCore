package ru.geekbrains.spring.cart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.Product;
import ru.geekbrains.spring.cart.CartService;
import ru.geekbrains.spring.daos.ProductRepository;

import java.util.HashMap;
import java.util.List;

@Component("cartService")
@Scope("prototype")
public class CartServiceImpl implements CartService {

    private static final Integer ONE_PRODUCT = 1;
    private final HashMap<Product, Integer> cart = new HashMap<>();
    private ProductRepository productRepository;

    @Override
    public void addAllOneByOne() {
        List<Long> productsIdList = productRepository.findAllProductsId();
        for (Long id : productsIdList) {
            addToCartById(id);
        }
    }

    @Override
    public void addToCartById(Long id) {
        addToCartById(id, ONE_PRODUCT);
    }

    @Override
    public void addToCartById(Long id, Integer amount) {

        Integer currentNumber = 0;

        Product product = productRepository.findProductById(id);
        if (cart.containsKey(product)) {
            currentNumber = cart.get(product);
        }

        cart.put(product, currentNumber + amount);
    }

    @Override
    public void emptyTrash() {
        cart.clear();
    }

    public String getFinalCart(){
        return cart.toString();
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
