package com.example.productcatalog.web.dto;

import java.util.List;

public class PageResponse<T> {

    private final List<T> data;
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalElements;

    public PageResponse(List<T> data, int page, int size, int totalPages, long totalElements) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
