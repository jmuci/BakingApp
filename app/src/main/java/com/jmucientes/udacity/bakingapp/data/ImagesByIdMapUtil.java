package com.jmucientes.udacity.bakingapp.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Util class to associate images by ID to different recipe items.
 */
public class ImagesByIdMapUtil {

    private static final Map<Integer, String> idToImageMap;
    static {
        Map<Integer, String> map = new HashMap<>(4);
        map.put(1, "http://eatdrinkpaleo.com.au/wp-content/uploads/2014/04/paleo-chocolate-tart-800-h2.jpg");
        map.put(2, "https://images.media-allrecipes.com/userphotos/720x405/3850414.jpg");
        map.put(3, "https://prods3.imgix.net/images/articles/2017_08/Non-Feature-yellow-cake-chocolate-frosting-recipe-dessert.jpg?auto=format%2Ccompress&dpr=2&ixjsv=2.2.3&q=50&w=750");
        map.put(4, "https://kitchenfunwithmy3sons.com/wp-content/uploads/2017/04/Easy-Lemon-Blueberry-Cheesecake-Dessert-680x453.jpg");
        idToImageMap = Collections.unmodifiableMap(map);
    }
    public static String getImageUriFromId(int id) {
        return idToImageMap.get(id);
    }
}