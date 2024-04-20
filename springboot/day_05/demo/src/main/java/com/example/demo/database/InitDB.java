package com.example.demo.database;

import com.example.demo.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Khởi tạo dữ liệu");
        PersonDB.people = fileReader.readFile("people.json");

        // In ra danh sách các person
        PersonDB.people.forEach(System.out::println);

        // In ra số lượng person
        System.out.println("Số lượng person: " + PersonDB.people.size());
    }
}
