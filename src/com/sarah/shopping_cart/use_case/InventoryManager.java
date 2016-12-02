package com.sarah.shopping_cart.use_case;

import com.sarah.shopping_cart.models.Book;
import com.sarah.shopping_cart.models.CD;
import com.sarah.shopping_cart.models.Item;
import com.sarah.shopping_cart.repository.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryManager {

    private final Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Item> getAllItems() {
        return inventory.getItems();
    }

    public List<CD> filterCDs() {
        List<CD> cds = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            if (item instanceof CD) {
                cds.add((CD) item);
            }
        }
        return cds;
    }

    public List<Book> filterBooks() {
        List<Book> books = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            if (item instanceof Book) {
                books.add((Book) item);
            }
        }
        return books;
    }

    public List<Item> filterItemsByName(String name) {
        List<Item> items = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                items.add(item);
            }
        }
        return items;
    }

    public Item findItemById(Long id) {
        for (Item item : inventory.getItems()) {
            if (Objects.equals(item.getId(), id)) {
                return item;
            }
        }
        return null;
    }
}
