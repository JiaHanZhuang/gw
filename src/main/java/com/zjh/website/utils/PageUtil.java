package com.zjh.website.utils;

import java.util.List;

/**
 * @author 庄家瀚
 * 用于分页的工具类,存储page放回的数据
 */
public class PageUtil<T>{

    /**
     * 当前页数
     */
    private int pageNumber;

    /**
     * 页面的数据量
     */
    private int pageSize;

    /**
     * 分页总数
     */
    private int totalPages;

    /**
     * 当前页上的元素数
     */
    private int numberOfElements;

    /**
     * 元素总数
     */
    private long totalElements;

    /**
     * 是否有上一页
     */
    private boolean hasPerviousPage;

    /**
     * 当前是否为第一页
     */
    private boolean hasFirstPage;

    /**
     * 是否有下一页
     */
    private boolean hasNextPage;

    /**
     * 当前是否为最后一页
     */
    private boolean hasLastPage;

    /**
     * 是否有内容
     */
    private boolean hasContent;

    /**
     * 内容
     */
    private List<T> content;

    public int getPageNumber() {
        return pageNumber;
    }

    public PageUtil<T> setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageUtil<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PageUtil<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public PageUtil<T> setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public PageUtil<T> setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public boolean isHasPerviousPage() {
        return hasPerviousPage;
    }

    public PageUtil<T> setHasPerviousPage(boolean hasPerviousPage) {
        this.hasPerviousPage = hasPerviousPage;
        return this;
    }

    public boolean isHasFirstPage() {
        return hasFirstPage;
    }

    public PageUtil<T> setHasFirstPage(boolean hasFirstPage) {
        this.hasFirstPage = hasFirstPage;
        return this;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public PageUtil<T> setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
        return this;
    }

    public boolean isHasLastPage() {
        return hasLastPage;
    }

    public PageUtil<T> setHasLastPage(boolean hasLastPage) {
        this.hasLastPage = hasLastPage;
        return this;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public PageUtil<T> setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public PageUtil<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }
}
