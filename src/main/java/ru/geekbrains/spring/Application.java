package ru.geekbrains.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.spring.cart.CartService;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CartService cartService = context.getBean("cartService", CartService.class);
        System.out.println("Введите id товара и количество(необяз.), которое вы хотите добавить в свою корзину: ");
        System.out.println("Или введите 'new' для получения новой корзины");
        System.out.println("Для выхода введите 'exit");
        System.out.println();

        while (true) {
            System.out.println("Программ ожидает ввода");
            Scanner scanner = new Scanner(System.in);
            String scannerString = scanner.nextLine();
            if (scannerString.equals("exit")) {
                System.out.println("Ваша корзина: " + cartService.getFinalCart());
                break;
            }
            if (scannerString.equals("new")) {
                cartService = context.getBean("cartService", CartService.class);
                continue;
            }
            String[] id = scannerString.split(" ");
            try {
                if (id.length == 1) {
                    cartService.addToCartById(Long.valueOf(id[0]));
                }
                if (id.length == 2) {
                    cartService.addToCartById(Long.valueOf(id[0]), Integer.valueOf(id[1]));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }
}
