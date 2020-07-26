package com.dunzo.machine;

import com.dunzo.model.Inventory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RefillJob implements Job {

    /**
     *
     * Job to refill the inventory after specified interval
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello! Time to refill machine");
        Boolean flag = Inventory.refillIngredients();
        if(flag) {
            System.out.println("Machine refilled. ");
        }
        else {
            System.out.println("Nothing to refill");
        }
    }
}
