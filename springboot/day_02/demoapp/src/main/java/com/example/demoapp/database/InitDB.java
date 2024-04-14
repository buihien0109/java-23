package com.example.demoapp.database;

import com.example.demoapp.model.Book;
import com.example.demoapp.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDB implements CommandLineRunner {

    @Qualifier("excelFileReader") // Ưu tiên xem bean nào được sử dụng
    @Autowired
    private IFileReader fileReader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Khởi tạo dữ liệu");
        BookDB.books = fileReader.readFile("books.xlsx");

        // In ra danh sách các sách
        for (Book book : BookDB.books) {
            System.out.println(book);
        }

        System.out.println("Số lượng sách: " + BookDB.books.size());
    }
}
