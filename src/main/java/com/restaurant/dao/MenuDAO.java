package com.restaurant.dao;


import com.restaurant.models.Item;

import java.util.List;

public interface MenuDAO {
    List<Item> getItems(String name) throws Exception;

    boolean addItem(Item type) throws Exception;

    boolean deleteItem(Item type) throws Exception;

    boolean updateItem(Item type) throws Exception;

    List<Item> filterItems(String category) throws Exception;

}
