package com.dunzo.machine;

import com.dunzo.model.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class DispenserRunnable implements Runnable {

    // variable that keeps the count of beverages processed
    private static Integer beverageCountPrepared = 0;

    String beverage;
    HashMap<String, Integer> recipe;
    ReentrantLock lock = new ReentrantLock();

    DispenserRunnable(String beverage, HashMap<String, Integer> recipe) {
        this.beverage = beverage;
        this.recipe = recipe;
    }

    @Override
    public void run() {
        /***
         * Synchronized is used here to make at one point of time only one beverage is updating the inventory
         *
         */
        lock.lock();
        try {
            if (checkAllIngredientsExists(beverage, recipe)) {
                setBeverageCountPrepared();
            }
        }
        finally {
            lock.unlock();
        }
    }

    /***
     *
     * This function checks whether all the required ingredients are present or not.
     * If not, print that beverage cannot be prepared.
     */
    public Boolean checkAllIngredientsExists(String beverage, HashMap<String, Integer> recipe) {
        for (Map.Entry<String, Integer> recipe_content : recipe.entrySet()) {
            if(Inventory.getIngredients().containsKey(recipe_content.getKey())
                    && Inventory.getIngredients().get(recipe_content.getKey()) >= recipe_content.getValue()) {
                continue;
            }
            else {
                if(! Inventory.getIngredients().containsKey(recipe_content.getKey())) {
                    System.out.println(beverage + " cannot be prepared because "+ recipe_content.getKey() + " is not available");
                }
                else {
                    System.out.println(beverage + " cannot be prepared because "+ recipe_content.getKey() + " is not sufficient");
                }
                return false;
            }
        }
        for (Map.Entry<String, Integer> recipe_content : recipe.entrySet()) {
            Integer currentQuantity = Inventory.getIngredients().get(recipe_content.getKey()) - recipe_content.getValue();
            Inventory.getIngredients().put(recipe_content.getKey(), currentQuantity);
        }
        return true;
    }

    public synchronized static Integer getBeverageCountPrepared() {
        return beverageCountPrepared;
    }

    public synchronized static void setBeverageCountPrepared() {
        DispenserRunnable.beverageCountPrepared = DispenserRunnable.beverageCountPrepared + 1;
    }

}
