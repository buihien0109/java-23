package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CSVFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        System.out.println("Đọc file CSV"); // Sử dụng thư viện OpenCSV để đọc file CSV

        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            CSVReader csvReader = new CSVReader(reader);
            csvReader.skip(1); // Bỏ qua dòng đầu tiên (header)

            ColumnPositionMappingStrategy<Book> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Book.class);
            strategy.setColumnMapping(new String[]{"id", "title", "author", "year"});

            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(csvReader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Book> books = csvToBean.parse();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();  // Trả về danh sách trống nếu có lỗi
    }
}
