package com.example.demo.response;

import java.util.List;

public interface PageResponse<T> {
    int getCurrentPage();

    List<T> getContent();

    int getPageSize();

    int getTotalPages();

    int getTotalElements();
}
