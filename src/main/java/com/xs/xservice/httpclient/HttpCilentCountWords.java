/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.httpclient;

import com.xs.xservice.httpclient.exception.ServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author serega
 */
public class HttpCilentCountWords implements CountWordsService {
    
    private static final String URL = "http://localhost:4567/count?str=";
    
    private static final HttpClient CLIENT = HttpClientBuilder.create().build();
    
    HttpCilentCountWords(){
    
    }
    
    public static class HttpCilentCountWordsHundler{
        private static final HttpCilentCountWords INSTANCE = new HttpCilentCountWords(); 
    }
    
    public static HttpCilentCountWords getInstance(){
        return HttpCilentCountWordsHundler.INSTANCE;
    }
    
    @Override
    public Integer countWords(String str) {
        try {
            HttpGet request = new HttpGet(new StringBuilder().append(URL).append(str).toString());
            HttpResponse response = CLIENT.execute(request);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }            
//        System.out.println("state = " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {

                return Integer.parseInt(result.toString());
            }
            throw new ServiceException(response.getStatusLine().getStatusCode());
        } catch (IOException | UnsupportedOperationException e) {
            System.out.println("Не предвиденная ошибка сервиса! " + e.getMessage());
            throw new IllegalArgumentException("Не предвиденная ошибка сервиса! " + e.getMessage());
        }
    }
    
}
