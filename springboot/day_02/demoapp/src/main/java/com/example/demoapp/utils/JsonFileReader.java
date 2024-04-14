package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
//@Primary
public class JsonFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        System.out.println("Đọc file JSON");
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Đọc file JSON và chuyển đổi nó thành danh sách các đối tượng Book
            return mapper.readValue(Paths.get(path).toFile(), new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();  // Trả về danh sách trống nếu có lỗi
    }
}
