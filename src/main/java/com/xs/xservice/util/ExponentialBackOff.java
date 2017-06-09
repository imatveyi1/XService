/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.util;

import java.util.Random;

/**
 *
 * @author Matvey
 */
public class ExponentialBackOff {
    
    private final int slotTime = 3000;
    private int count = -1;
    
    public long getDelay(){
        count++;
        if(count <= 2){
            return (count) * slotTime;
        }
        
        Random r = new Random();
        return (r.nextInt(1<<count)) * slotTime;
        
    }
    
}
