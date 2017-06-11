/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.httpclient;

import com.xs.xservice.util.SplitString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * @author Matvey
 */
public class FileCountWordsService implements CountWordsService {

    private final RetryCountWords retryService = new RetryCountWords();
    
    @Override
    public Integer countWords(String path) {
        String str = "";
        int countWords = 0;
        try {
            BufferedReader bReader = getReader(path);
            BufferedReader fin = bReader;         
            String line;
            while ((line = fin.readLine()) != null) str += line;
            List<String> strList = SplitString.split(URLEncoder.encode(str.trim(), "UTF-8"));
            
            countWords = strList.stream().map((s) -> retryService.countWords(s)).reduce(countWords, Integer::sum);
            
        } catch (IOException ex) {
            throw new IllegalArgumentException("Не предвиденная ошибка!");
        }          
        return countWords;
    }
    
    private static BufferedReader getReader(String pathToFile) throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToFile)), "UTF-8"));
    }
    
}
