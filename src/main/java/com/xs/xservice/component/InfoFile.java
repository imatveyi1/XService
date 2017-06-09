/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.xservice.component;

import java.io.Serializable;

/**
 *
 * @author serega
 */
public class InfoFile implements Serializable {
    
    private final int countWords;
    private final String nameFile;
    private final int countAccessDenied;

    public InfoFile(int countWords, String nameFile, int countAccessDenied) {
        this.countWords = countWords;
        this.nameFile = nameFile;
        this.countAccessDenied = countAccessDenied;
    }

    public int getCountWords() {
        return countWords;
    }

    public String getNameFile() {
        return nameFile;
    }

    public int getCountAccessDenied() {
        return countAccessDenied;
    }
    
}
