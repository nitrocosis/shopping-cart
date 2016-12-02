package com.sarah.shopping_cart.use_case;

import com.sarah.shopping_cart.models.Item;

import java.util.ArrayList;
import java.util.List;

public class CheckoutManager {

    private final InventoryManager inventoryManager;
    private final List<Long> itemIds = new ArrayList<>();

    public CheckoutManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void addToCart(Long itemId) {
        itemIds.add(itemId);
    }

    public void removeFromCart(Long itemId) {
        itemIds.remove(itemId);
    }

    public boolean isItemInCart(Long itemId) {
        return itemIds.contains(itemId);
    }

    @Override
    public String toString() {
        int total = 0;
        StringBuilder items = new StringBuilder();

        for (Long itemId : itemIds) {
            Item item = inventoryManager.findItemById(itemId);
            total += item.getPrice();
            items.append(item);
            items.append('\n');
        }

        return "Cart: total=$" + total + ", items=\n" + items;
    }
}
