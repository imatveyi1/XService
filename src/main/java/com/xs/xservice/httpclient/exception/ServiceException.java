/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.httpclient.exception;

/**
 *
 * @author serega
 */
public class ServiceException extends Exception {
    
    private final int status;

    public ServiceException(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    
}
