package com.accion.dto;

/**
 * This class will be used as transfer object between controller and DTO  layers
 */
public class SearchQueryDTO {
    int page;
    int size;
    String sortOrder;
    String elementType;
    String name;
    String categoryType;
    String sortBy;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * Below constructor will build the DTO with all parameters
     * @param page
     * @param size
     * @param sortBy
     * @param sortOrder
     * @param name
     */
    public SearchQueryDTO(int page, int size, String sortBy, String sortOrder, String name) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.name = name;
    }

    public SearchQueryDTO(int page, int size, String sortBy, String sortOrder) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;

    }

    public SearchQueryDTO(int page, int size, String sortBy, String sortOrder, String categoryType, String name) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.categoryType=categoryType;
        this.name=name;

    }




}
