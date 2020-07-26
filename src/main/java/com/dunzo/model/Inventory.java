package com.dunzo.model;

import java.util.Date;
import java.util.HashMap;

/***
Class containing all the ingredients
 */
public class Inventory {
    private static HashMap<String, Integer> ingredients = new HashMap<String, Integer>();
    private static HashMap<String, Integer> initial_ingredients = new HashMap<String, Integer>();

    /***
     * Initialising the ingredients
     *
     * @param ingredient is the name of each item
     * @param quantity is the unit of each item available
     */
    public static void addIngredients(String ingredient, Integer quantity) {
        if (ingredients.containsKey(ingredient)) {
            Integer existingQuantity = ingredients.get(ingredient);
            ingredients.put(ingredient, existingQuantity + quantity);
            initial_ingredients.put(ingredient, existingQuantity + quantity);
        }
        else {
            ingredients.put(ingredient, quantity);
            initial_ingredients.put(ingredient, quantity);
        }
    }

    /***
     *  Refill the inventory by copying the values from the one that we have at the start
     */
    public static Boolean refillIngredients() {
        Boolean flag = false;
        for (String key: ingredients.keySet()) {
            if (ingredients.get(key) != initial_ingredients.get(key)) {
                Integer initialQuantity = initial_ingredients.get(key);
                ingredients.put(key, initialQuantity);
                flag = true;
            }
        }
        return flag;
    }

    public static HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "ingredients=" + ingredients +
                '}';
    }
}
