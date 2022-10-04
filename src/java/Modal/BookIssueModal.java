/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.LibraryHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class BookIssueModal {

    String table_name = "borrower";

    LibraryHelper dbHL;

    public boolean issue(String patron_Id, String admin_Id, ArrayList<String> books) throws SQLException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);

        for (String book : books) {

            dbHL.insertBorrower(patron_Id, admin_Id, book);

        }
        dbHL.closeConnection();
        return true;
    }

    public boolean returned(String patron_Id, ArrayList<String> books) throws SQLException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);

        for (String book : books) {

            dbHL.updateBorrower(patron_Id, book);

        }
        dbHL.closeConnection();
        return true;
    }

    public boolean checkTaken(String patron, String id) throws SQLException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);
        int condition = 0;
        ResultSet results = dbHL.checkBookExists(patron, id);
        while (results.next()) {
            condition = results.getInt("num");
        }
        dbHL.closeConnection();
        if (condition > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getBookQty(String book) throws SQLException, ClassNotFoundException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);
        int condition = 0;
        ResultSet results = dbHL.fetchBookQty(book);
        while (results.next()) {
            condition = results.getInt("qty");
        }
         dbHL.closeConnection();
        return condition;
    }
    
    public int getBookTotal(String book) throws SQLException, ClassNotFoundException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);
        int condition = 0;
        ResultSet results = dbHL.fetchBookTotal(book);
        while (results.next()) {
            condition = results.getInt("ava");
        }
         dbHL.closeConnection();
        return condition;
    }
    
     public void decBookQty(String book) throws SQLException, ClassNotFoundException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);
        dbHL.triggerBookQty(book,"-");
         dbHL.closeConnection();
    }
     
     public void incBookQty(String book) throws SQLException, ClassNotFoundException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);
        dbHL.triggerBookQty(book,"+");
         dbHL.closeConnection();
    }
     
      public void setBookAvailable(String book,boolean status) throws SQLException, ClassNotFoundException, ClassNotFoundException {
        dbHL = new LibraryHelper(table_name);
        int val = 0;
        if(status){val = 1;}
        
        dbHL.trigger(book,val);
         dbHL.closeConnection();
    }
      
        
    public void increaseAmount(String patron_Id,int amount) throws SQLException, ClassNotFoundException{
        dbHL = new LibraryHelper("patron");
        
        dbHL.increase("patron_Qty","patron_Id",patron_Id,amount);
         dbHL.closeConnection();
    }
    
    public void decreaseAmount(String patron_Id,int amount) throws SQLException, ClassNotFoundException{
        dbHL = new LibraryHelper("patron");
        
        dbHL.decrease("patron_Qty","patron_Id",patron_Id,amount);
         dbHL.closeConnection();
    }
}
