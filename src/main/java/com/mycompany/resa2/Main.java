/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resa2;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author bacvikto
 */
public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException 
    {
        // TODO code application logic here
       
        //Skapar en ny instans av vår UserInterface-klass
        new UserInterface();
    }
    
}
