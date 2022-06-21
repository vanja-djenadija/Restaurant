package com.restaurant.dao;

import java.util.List;

public interface GenericCategoryDAO<T> {
    List<T> getCategory(String name) throws Exception;

    boolean addCategory(T type) throws Exception;

    boolean deleteCategory(T type) throws Exception;
}
