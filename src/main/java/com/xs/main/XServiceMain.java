/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.main;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.xs.xservice.component.CountWordsActor;
import com.xs.xservice.httpclient.FileCountWordsService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import scala.concurrent.Await;
import scala.concurrent.CanAwait;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 *
 * @author serega
 */
public class XServiceMain {

    static class MyCanAwait implements CanAwait{
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        Stream.of("file1", "file2", "file3", "file4")
                .map(System::getProperty)
                .map( (p) ->  Props.create(CountWordsActor.class, () -> new CountWordsActor(p)))
                .map(prop -> ActorSystem.create().actorOf(prop))
                .map( actor -> Patterns.ask(actor, new FileCountWordsService(), 500000L))
                .forEach((Future<Object> future) -> {
            try {
                System.out.println("count words = " +  Await.result(future, Duration.create(500000L, TimeUnit.MILLISECONDS)));
            } catch (Exception ex) {
                Logger.getLogger(XServiceMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     
    }
    
}
