/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author WorldEdit
 */
public class ExceedsTotalException extends Exception{
    public ExceedsTotalException ()  
    {  
        System.out.println("There are no currently Physically available Books!!");
    } 
}