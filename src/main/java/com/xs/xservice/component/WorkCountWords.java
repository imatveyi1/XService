/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.component;

import com.xs.xservice.httpclient.HttpCilentCountWords;
import com.xs.xservice.httpclient.exception.ServiceException;
import com.xs.xservice.util.ExponentialBackOff;
import com.xs.xservice.util.SplitString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author serega
 */
public class WorkCountWords {
    
    private static final HttpCilentCountWords CLIENT = HttpCilentCountWords.getInstance();
    
    private int countAccessDenied = 0;
    private int countWords = 0;
    private final String pathFile;

    public WorkCountWords(String pathFile) {
        this.pathFile = pathFile;
    }
    
    public InfoFile getCountWords() throws IOException, InterruptedException{
        BufferedReader bReader = getReader(pathFile);
 
         BufferedReader fin = bReader;
         String str = "";
         String line;
         while ((line = fin.readLine()) != null) str += line;
         
//            System.out.println("length = " + str.length());
            List<String> strMass = SplitString.split(str);
            strMass.forEach((s) ->{                
                    countWords += get(s, new ExponentialBackOff());                
            });
            return new InfoFile(countWords, pathFile, countAccessDenied);
            
    }
    
    private Integer get(String str, ExponentialBackOff exp) {
        long time = exp.getDelay();
//        System.out.println(pathFile + " time = " + time);
        
        try {
            Thread.sleep(time);
            return CLIENT.countWords(str);
        } catch (ServiceException ex) {
            if(ex.getStatus() == 403){
                countAccessDenied++;
            }
        } catch (IOException|InterruptedException ex) {
            throw new IllegalArgumentException(ex);
        }
        return get(str, exp);
    }
    
    private static BufferedReader getReader(String pathToFile) throws FileNotFoundException, UnsupportedEncodingException{
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToFile)), "UTF-8"));
    }
    
}
