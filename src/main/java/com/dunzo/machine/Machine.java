package com.dunzo.machine;

import com.dunzo.model.Beverages;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Machine {

    public void startMachine(Beverages beverages, int n) {
        //Created thread pool of the number equal to the outlets
        ExecutorService executor = Executors.newFixedThreadPool(n);

        //Passing all the beverages for processing
        for (Map.Entry<String, HashMap> map : beverages.getBeverages().entrySet()) {
            String beverage = (String)map.getKey();
            HashMap<String, Integer>  recipe = map.getValue();
            Runnable worker = new DispenserRunnable(beverage, recipe);
            executor.execute(worker);
        }
        executor.shutdown();

    }
}
