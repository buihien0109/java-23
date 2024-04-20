package com.example.demo.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseImpl<T> implements PageResponse<T> {
    List<T> data;
    int currentPage;
    int pageSize;

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public List<T> getContent() {
        return data.subList((currentPage - 1) * pageSize, Math.min(currentPage * pageSize, data.size()));
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) data.size() / pageSize);
    }

    @Override
    public int getTotalElements() {
        return data.size();
    }
}
