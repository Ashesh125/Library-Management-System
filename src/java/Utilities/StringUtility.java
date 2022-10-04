/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author WorldEdit
 */
public class StringUtility {
    public static String removeLastChar(String s){  
        //returns the string after removing the last character  
        return s.substring(0, s.length() - 1);  
    }
}
