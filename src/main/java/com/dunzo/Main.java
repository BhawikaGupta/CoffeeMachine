package com.dunzo;

import com.dunzo.machine.Machine;
import com.dunzo.machine.RefillJob;
import com.dunzo.model.Beverages;
import com.dunzo.model.Inventory;
import com.dunzo.model.Recipe;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

    /***
     *
     * The below function takes forms all the required input
     * starting from number of machines, ingredients available and beverages
     */
    public static void main(String[] args) throws SchedulerException {
        int n = 4; // initialising the outlet number

        Inventory.addIngredients("hot_water", 500);
        Inventory.addIngredients("hot_milk", 500);
        Inventory.addIngredients("ginger_syrup",100);
        Inventory.addIngredients("sugar_syrup", 100);
        Inventory.addIngredients("tea_leaves_syrup", 100);

        Beverages beverages = new Beverages();// adding the beverages with the recipe

        beverages.addBeverage("hot_tea");
        Recipe recipeHotTea = new Recipe();
        recipeHotTea.addIngredients("hot_water", 200);
        recipeHotTea.addIngredients("hot_milk", 100);
        recipeHotTea.addIngredients("ginger_syrup", 10);
        recipeHotTea.addIngredients("sugar_syrup", 10);
        recipeHotTea.addIngredients("tea_leaves_syrup", 30);
        beverages.addRecipe("hot_tea", recipeHotTea);

        beverages.addBeverage("hot_coffee");
        Recipe recipeHotCoffee = new Recipe();
        recipeHotCoffee.addIngredients("hot_water", 100);
        recipeHotCoffee.addIngredients("hot_milk", 400);
        recipeHotCoffee.addIngredients("ginger_syrup", 30);
        recipeHotCoffee.addIngredients("sugar_syrup", 50);
        recipeHotCoffee.addIngredients("tea_leaves_syrup", 30);
        beverages.addRecipe("hot_coffee", recipeHotCoffee);

        beverages.addBeverage("black_tea");
        Recipe recipeBlackTea =  new Recipe();
        recipeBlackTea.addIngredients("hot_water", 300);
        recipeBlackTea.addIngredients("tea_leaves_syrup", 30);
        recipeBlackTea.addIngredients("ginger_syrup", 30);
        recipeBlackTea.addIngredients("sugar_syrup", 50);
        beverages.addRecipe("black_tea", recipeBlackTea);

        beverages.addBeverage("green_tea");
        Recipe recipeGreenTea =  new Recipe();
        recipeGreenTea.addIngredients("hot_water", 100);
        recipeGreenTea.addIngredients("green_mixture", 30);
        recipeGreenTea.addIngredients("ginger_syrup", 30);
        recipeGreenTea.addIngredients("sugar_syrup", 50);
        beverages.addRecipe("green_tea", recipeGreenTea);

        //Main method that run the coffee machine by taking all the required inputs
        Machine machine = new Machine();
        machine.startMachine(beverages, n);


        //Function to refill the inventory every 5 seconds
        JobDetail job = JobBuilder.newJob(RefillJob.class).build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }
}
