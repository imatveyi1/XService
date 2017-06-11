/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.httpclient;

import com.xs.xservice.httpclient.exception.ServiceException;
import com.xs.xservice.util.ExponentialBackOff;

/**
 *
 * @author Matvey
 */
public class RetryCountWords implements CountWordsService {

    private final HttpCilentCountWords clientService = new HttpCilentCountWords(); 
    
    @Override
    public Integer countWords(String str) {
        return get(str, new ExponentialBackOff());
    }
    
    private Integer get(String str, ExponentialBackOff exp) {
        long time = exp.getDelay();
        System.out.println(" time = " + time);
        
        try {
            Thread.sleep(time);
            return clientService.countWords(str);
        } catch (ServiceException ex) {
            System.out.println("Response status " + ex.getStatus());
            if(ex.getStatus() == 403){
                System.out.println("Response status 403");
            }
        } catch (InterruptedException ex) {
            throw new IllegalArgumentException("Не предвиденная ошибка!");
        }
        return get(str, exp);
    }
    
}
