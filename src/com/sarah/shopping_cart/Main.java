package com.sarah.shopping_cart;

import com.sarah.shopping_cart.models.Book;
import com.sarah.shopping_cart.models.CD;
import com.sarah.shopping_cart.repository.Inventory;
import com.sarah.shopping_cart.repository.UserRepository;
import com.sarah.shopping_cart.use_case.CheckoutManager;
import com.sarah.shopping_cart.use_case.InventoryManager;
import com.sarah.shopping_cart.use_case.UserSessionManager;

public class Main {

    public static void main(String[] args) {
        UserSessionManager userSessionManager = new UserSessionManager(new UserRepository());
        InventoryManager inventoryManager = new InventoryManager(createInventory());
        CheckoutManager checkoutManager = new CheckoutManager(inventoryManager);
        ShoppingCartProgram program = new ShoppingCartProgram(userSessionManager, inventoryManager, checkoutManager);

        program.start();
    }

    private static Inventory createInventory() {
        Inventory inventory = new Inventory();

        long id = 1;
        inventory.addItem(CD.builder().setId(id++).setName("Love Yourself").setArtist("Justin Beiber").setPrice(3)
                .setMusicFilePath("cd/love-yourself.mp3")
                .build());
        inventory.addItem(CD.builder().setId(id++).setName("My Love").setArtist("Justin Timberlake").setPrice(2)
                .setMusicFilePath("cd/my-love.mp3")
                .build());
        inventory.addItem(CD.builder().setId(id++).setName("Kiss Kiss").setArtist("Chris Brown").setPrice(2)
                .setMusicFilePath("cd/kiss-kiss.mp3")
                .build());

        inventory.addItem(Book.builder().setId(id++).setName("Harry Potter and the Sorcerer's Stone")
                .setAuthor("J. K. Rowling").setPrice(10)
                .setBookFilePath("book/sorcerer-stone.jpg")
                .build());
        inventory.addItem(Book.builder().setId(id++).setName("Harry Potter and the Chamber of Secrets")
                .setAuthor("J. K. Rowling").setPrice(10)
                .setBookFilePath("book/chamber-of-secrets.jpg")
                .build());
        inventory.addItem(Book.builder().setId(id++).setName("Harry Potter and the Prisoner of Azkaban")
                .setAuthor("J. K. Rowling").setPrice(10)
                .setBookFilePath("book/prisoner-of-azkaban.jpg")
                .build());

        return inventory;
    }
}
