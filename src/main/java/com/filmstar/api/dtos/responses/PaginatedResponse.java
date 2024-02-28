package com.filmstar.api.dtos.responses;

public class PaginatedResponse<T> {

    private Long size;
    private Integer pages;
    private Integer currentPage;
    private T content;
    
    public PaginatedResponse() {
    	
    }

    public PaginatedResponse(Long size, Integer pages, Integer currentPage, T content) {
        this.size = size;
        this.pages = pages;
        this.currentPage = currentPage;
        this.content = content;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
