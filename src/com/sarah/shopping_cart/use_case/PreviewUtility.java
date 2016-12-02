package com.sarah.shopping_cart.use_case;

import com.sarah.shopping_cart.models.Book;
import com.sarah.shopping_cart.models.CD;
import com.sarah.shopping_cart.models.Item;

import java.awt.*;
import java.io.File;
import java.net.URL;

public final class PreviewUtility {

    private static final String BASE_RESOURCE_PATH = "../../../../inventory/";

    private PreviewUtility() {
    }

    public static boolean previewItem(Item item) {
        String filePath = "";

        if (item instanceof CD) {
            filePath = ((CD) item).getMusicFilePath();
        } else if (item instanceof Book) {
            filePath = ((Book) item).getBookFilePath();
        }

        // TODO this will be null when ran from jar
        URL fileURL = PreviewUtility.class.getResource(BASE_RESOURCE_PATH + filePath);
        try {
            File file = new File(fileURL.getPath());
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
