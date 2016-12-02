package com.sarah.shopping_cart;

import com.sarah.shopping_cart.models.Book;
import com.sarah.shopping_cart.models.CD;
import com.sarah.shopping_cart.models.Item;
import com.sarah.shopping_cart.use_case.CheckoutManager;
import com.sarah.shopping_cart.use_case.InventoryManager;
import com.sarah.shopping_cart.use_case.PreviewUtility;
import com.sarah.shopping_cart.use_case.UserSessionManager;

import java.util.List;
import java.util.Scanner;

class ShoppingCartProgram {

    private final UserSessionManager userSessionManager;

    private final InventoryManager inventoryManager;

    private final CheckoutManager checkoutManager;

    private final Scanner scanner = new Scanner(System.in);

    ShoppingCartProgram(UserSessionManager userSessionManager, InventoryManager inventoryManager, CheckoutManager checkoutManager) {
        this.userSessionManager = userSessionManager;
        this.inventoryManager = inventoryManager;
        this.checkoutManager = checkoutManager;
    }

    void start() {
        System.out.println("Welcome to the shopping cart program!");

        while (!userSessionManager.isLoggedIn()) {
            authenticate();
        }

        while (userSessionManager.isLoggedIn()) {
            shopAround();
        }

        System.out.println("Goodbye, thanks for shopping!");
    }

    private void shopAround() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("(1) Show all items");
        System.out.println("(2) Show all CDs");
        System.out.println("(3) Show all books");
        System.out.println("(4) Filter items by name");
        System.out.println("(5) Preview an item");
        System.out.println("(6) Add an item to cart");
        System.out.println("(7) Remove an item from cart");
        System.out.println("(8) Show what's in your cart");
        System.out.println("(9) Quit and logout");

        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            notifyIncorrectChoice();
            return;
        }

        System.out.println();
        switch (choice) {
            case 1:
                showAllItems();
                break;
            case 2:
                showAllCDs();
                break;
            case 3:
                showAllBooks();
                break;
            case 4:
                filterItemsByName();
                break;
            case 5:
                previewItem();
                break;
            case 6:
                addItemToCart();
                break;
            case 7:
                removeItemFromCart();
                break;
            case 8:
                showCart();
                break;
            case 9:
                quitAndLogout();
                break;
            default:
                notifyIncorrectChoice();
        }
    }

    private void quitAndLogout() {
        System.out.println("Quitting and logging you out...");
        userSessionManager.logout();
    }

    private void showCart() {
        System.out.println("These are the items you currently have in your cart:");
        System.out.println(checkoutManager.toString());
    }

    private void removeItemFromCart() {
        System.out.println("Enter the ID of the item you would like to remove from your cart:");
        Long itemId;
        try {
            itemId = scanner.nextLong();
        } catch (Exception e) {
            notifyIncorrectChoice();
            return;
        }

        System.out.println();
        if (checkoutManager.isItemInCart(itemId)) {
            Item item = inventoryManager.findItemById(itemId);
            checkoutManager.removeFromCart(itemId);
            System.out.println(item.getName() + " has been removed from your cart.");
        } else {
            System.out.println("Item with ID does not exist in your cart!");
        }
    }

    private void previewItem() {
        System.out.println("Enter the ID of the item you would like to preview:");

        Long itemId;
        try {
            itemId = scanner.nextLong();
        } catch (Exception e) {
            notifyIncorrectChoice();
            return;
        }

        System.out.println();
        Item item = inventoryManager.findItemById(itemId);
        if (item != null) {
            boolean success = PreviewUtility.previewItem(item);
            if (success) {
                System.out.println("Previewing " + item.getName() + "...");
            } else {
                System.out.println("Unable to preview " + item.getName());
            }
        } else {
            System.out.println("Item with ID does not exist!");
        }
    }

    private void addItemToCart() {
        System.out.println("Enter the ID of the item you would like to add:");

        Long itemId;
        try {
            itemId = scanner.nextLong();
        } catch (Exception e) {
            notifyIncorrectChoice();
            return;
        }

        System.out.println();
        Item item = inventoryManager.findItemById(itemId);
        if (item != null) {
            checkoutManager.addToCart(itemId);
            System.out.println(item.getName() + " has been added to cart.");
        } else {
            System.out.println("Item with ID does not exist!");
        }
    }

    private void filterItemsByName() {
        System.out.println("Enter the name of the items you wish to filter:");
        String name = scanner.next();
        System.out.println();

        List<Item> items = inventoryManager.filterItemsByName(name);
        if (items.isEmpty()) {
            System.out.println("There are no items that matched the name " + name);
        } else {
            System.out.println("The following items match " + name);
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    private void showAllBooks() {
        System.out.println("These are all of the available books:");
        for (Book book : inventoryManager.filterBooks()) {
            System.out.println(book);
        }
    }

    private void showAllCDs() {
        System.out.println("These are all of the available CDs:");
        for (CD cd : inventoryManager.filterCDs()) {
            System.out.println(cd);
        }
    }

    private void showAllItems() {
        System.out.println("These are all of the available items:");
        for (Item item : inventoryManager.getAllItems()) {
            System.out.println(item);
        }
    }

    private void authenticate() {
        System.out.println("Login (l) or signup (s)?");
        String choice = scanner.nextLine();
        switch (choice) {
            case "l":
                login();
                break;
            case "s":
                signup();
                break;
            default:
                notifyIncorrectChoice();
        }
    }

    private void login() {
        System.out.println("Enter email");
        String email = scanner.nextLine();

        System.out.println("Enter password");
        String password = scanner.nextLine();

        boolean success = userSessionManager.login(email, password);
        if (success) {
            System.out.println("Login success!");
        } else {
            System.out.println("Login failed because email or password is incorrect");
        }
    }

    private void signup() {
        System.out.println("Enter email");
        String email = scanner.nextLine();

        System.out.println("Enter password");
        String password = scanner.nextLine();

        boolean success = userSessionManager.signup(email, password);
        if (success) {
            System.out.println("Signup success!");
        } else {
            System.out.println("Signup failed because email is already taken or the email / password is not provided!");
        }
    }

    private void notifyIncorrectChoice() {
        System.out.println("Your choice is not valid.");
    }
}
