/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.component;

import akka.actor.AbstractActor;
import com.xs.xservice.httpclient.FileCountWordsService;

/**
 *
 * @author Matvey
 */
public class CountWordsActor extends AbstractActor {

    private final String path;

    public CountWordsActor(String path) {
        this.path = path;
    }
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
        .match(FileCountWordsService.class, fcv -> {
            sender().tell(fcv.countWords(path), self());
        }).build();
    }
    
}
