/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author WorldEdit
 */
public class InvalidISBNException extends Exception{
    public InvalidISBNException ()  
    {  
        System.out.println("ISBN must be a number of 10 or 13 digits and not any other value!!");
    } 
}
