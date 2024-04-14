package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        System.out.println("Đọc file Excel"); // Sử dụng thư viện Apache POI để đọc file Excel
        List<Book> books = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Bỏ qua dòng đầu tiên (header)
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row currentRow = rows.next();
                Book book = new Book();

                // Giả định cấu trúc file là ID, Title, Author, Year
                book.setId((int) currentRow.getCell(0).getNumericCellValue());
                book.setTitle(currentRow.getCell(1).getStringCellValue());
                book.setAuthor(currentRow.getCell(2).getStringCellValue());
                book.setYear((int) currentRow.getCell(3).getNumericCellValue());

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}
