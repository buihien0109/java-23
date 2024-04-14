package com.example.demostream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoStreamApplication.class, args);

        // Sử dụng class implement Greeting
        Greeting vietnam = new VietNam();
        vietnam.greet("Nguyễn Văn A");

        // Sử dụng Anonymous class
        Greeting china = new Greeting() {
            @Override
            public void greet(String name) {
                System.out.println("你好 " + name);
            }
        };
        china.greet("张三");

        // Sử dụng Lambda expression
        Greeting japan = (name) -> System.out.println("こんにちは " + name);
        japan.greet("山田太郎");

        Greeting english = (name) -> System.out.println("Hello " + name);
        english.greet("John Doe");

        // Sử dụng Lambda expression Calculator
        Calculator add = (a, b) -> a + b;
        System.out.println("5 + 3 = " + add.calculate(5, 3));

        Calculator substract = (a, b) -> a - b;
        System.out.println("5 - 3 = " + substract.calculate(5, 3));

        Calculator multiply = (a, b) -> a * b;
        System.out.println("5 * 3 = " + multiply.calculate(5, 3));

        Calculator divide = (a, b) -> a / b;
        System.out.println("5 / 3 = " + divide.calculate(5, 3));

        // Stream
        List<Integer> list = new ArrayList<>(
                List.of(1, 5, 3, 9, 7, 2, 1, 4)
        );
        // Lấy số lẻ -> x2 giá trị -> tính tổng
        int sum = list.stream()
                .filter(number -> number % 2 != 0)
                .map(number -> number * 2)
                .reduce(0, (total, number) -> total + number);
//                .mapToInt(Integer::intValue)
//                .sum();
        System.out.println("Tổng: " + sum);
    }
}
