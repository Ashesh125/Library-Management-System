/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author WorldEdit
 */
public class EmptyValueException extends Exception{
    public EmptyValueException (String str)  
    {  
        System.out.println("The value of " +str+" is empty !!");
    } 
}
