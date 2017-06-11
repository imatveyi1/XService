/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Matvey
 */
public class SplitString {

    private SplitString() {
    }
    
    public static List<String> split(String str){
        if(str.length()>2048){
            return getList(str);
        }
        return Arrays.asList(str);
    }
    
    private static List<String> getList(String str){
        List<String> res = new ArrayList();
        int count = (int) Math.ceil(str.length()/1024.0);
        
        int length = str.length()/count;
        for(int i=0; i < str.length();){
            int index = str.indexOf("+", (i + length)>=str.length()? str.length() : (i + length));
            String el = str.substring(i, index == -1 ? str.length() : index);
            res.add(el.trim());
            i = (index==-1 ? str.length() : index);
        }
        return res;
    }
    
}
