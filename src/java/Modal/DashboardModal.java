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
public class DashboardModal {
    LibraryHelper dbHL;
    ResultSet results;
    public String fetchTotal(String table) throws SQLException {
        try {
            switch(table){
                case "books":
                    dbHL = new LibraryHelper("books");
                    results = dbHL.fetchCount();
                    break;
                    
                case "booksTotal":
                    dbHL = new LibraryHelper("books");
                    results = dbHL.fetchCount("sum","","book_Total");
                    break;
                    
                 
                case "patrons":
                    dbHL = new LibraryHelper("patron");
                    results = dbHL.fetchCount();
                    break;
                    
                case "borrowers":
                    dbHL = new LibraryHelper("borrower");
                    results = dbHL.fetchCount("distinct","returned is NULL","patron_Id");
                    break;
                    
                case "bookIssued":
                    dbHL = new LibraryHelper("borrower");
                    results = dbHL.fetchCount("returned is NULL");
                    break;
                    
                case "blacklist":
                    dbHL = new LibraryHelper("patron");
                    results = dbHL.fetchCount("flagged = 1");
                    break;
                  
            }
            String num = "0";
            while (results.next()) {
              num = results.getString("total");
            }
            return num;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
               dbHL.closeConnection();
        }
         return null;
    }
    
    public ArrayList<String[]> fetchDateData(String type) throws SQLException {
        try {
            switch(type){
                case "issue":
                    dbHL = new LibraryHelper("borrower");
                    results = dbHL.fetchIssueDates();
                    break;
                
                case "returned":
                    dbHL = new LibraryHelper("borrower");
                    results = dbHL.fetchReturnDates();
                    break;
                
            }
            ArrayList<String[]> list = new ArrayList();
            
            while (results.next()) {
            String temp[] = new String[2];
                temp[0] = results.getString("dates");
                temp[1] = results.getString("totals");
                list.add(temp);
            }
            return list;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
               dbHL.closeConnection();
        }
         return null;
    }
}
