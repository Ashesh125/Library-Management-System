/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author WorldEdit
 */
public class NumberCheck {
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[+-]?[0-9]+");
    }
}
