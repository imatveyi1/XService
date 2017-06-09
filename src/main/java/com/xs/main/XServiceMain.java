/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xs.main;

import com.xs.xservice.component.InfoFile;
import com.xs.xservice.component.WorkCountWords;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author serega
 */
public class XServiceMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.println("path to file 1 ");
        String path1 =  in.nextLine();
        System.out.println("path to file 2 ");
        String path2 =  in.nextLine();
        System.out.println("path to file 3 ");
        String path3 =  in.nextLine();
        System.out.println("path to file 4 ");
        String path4 =  in.nextLine();
        System.out.println("working....");
        WorkCountWords wcw1 = new WorkCountWords(path1);
        WorkCountWords wcw2 = new WorkCountWords(path2);
        WorkCountWords wcw3 = new WorkCountWords(path3);
        WorkCountWords wcw4 = new WorkCountWords(path4);
        
        List<WorkCountWords> list = new ArrayList();
        list.add(wcw1);
        list.add(wcw2);
        list.add(wcw3);
        list.add(wcw4);
        list.stream().parallel().forEach((wcw)-> {
            try {
                InfoFile info = wcw.getCountWords();
                System.out.println("path file = " + info.getNameFile());
                System.out.println("count words = " + info.getCountWords());
                System.out.println("count 403 answers = " + info.getCountAccessDenied());
                System.out.println("------------------------------");
            } catch (InterruptedException|IOException ex) {
                throw new IllegalArgumentException(ex);
        }});
     
    }
    
}
