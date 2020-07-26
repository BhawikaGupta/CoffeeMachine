package com.dunzo.model;

import java.util.HashMap;
import java.util.List;

/***
 * Class containing all the beverages offered
 */
public class Beverages {
    HashMap<String, HashMap> beverages = new HashMap<String, HashMap>();

    public void addBeverage(String beverage) {
        beverages.put(beverage, new HashMap());
    }

    /**
     *
     * @param beverage_list of each beverage to be added
     */
    public void addBeverage(List<String> beverage_list) {
        for (String beverage : beverage_list) {
            beverages.put(beverage, new HashMap());
        }
    }

    /**
     *
     * @param beverage for which ingredients need to be added
     * @param recipe for each beverage
     */
    public void addRecipe(String beverage, Recipe recipe) {
        beverages.put(beverage, recipe.getRecipeIngredients());
    }

    public HashMap<String, HashMap> getBeverages() {
        return beverages;
    }

    @Override
    public String toString() {
        return "Beverages{" +
                "beverages=" + beverages +
                '}';
    }
}
