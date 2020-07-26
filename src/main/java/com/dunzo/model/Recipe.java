package com.dunzo.model;

import java.util.HashMap;

/***
Class containing recipe for all beverages
 */
public class Recipe {
    private HashMap<String, Integer> recipeIngredients = new HashMap<String, Integer>();

    /***
     * Initialising the ingredients
     *
     * @param ingredient is the name of each item
     * @param quantity is the unit of each item available
     */
    public void addIngredients(String ingredient, Integer quantity) {
        if (recipeIngredients.containsKey(ingredient)) {
            Integer existingQuantity = recipeIngredients.get(ingredient);
            recipeIngredients.put(ingredient, existingQuantity + quantity);
        }
        else {
            recipeIngredients.put(ingredient, quantity);
        }
    }

    public HashMap<String, Integer> getRecipeIngredients() {
        return recipeIngredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeIngredients=" + recipeIngredients +
                '}';
    }
}
