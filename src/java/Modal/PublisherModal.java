/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import ObjClasses.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @publisher WorldEdit
 */
public class PublisherModal {
    DatabaseHelper dbH;
    String table_name = "publishers";
    
    public boolean insertPublisher(Publisher publisher) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[1];
            values[0] =publisher.getName();
            boolean isInserted = dbH.insert(values);
            if (isInserted) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }
    
    public boolean deletePublisher(Publisher publisher) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            int isDeleted = dbH.delete(publisher.getId());
            if (isDeleted == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }
    
     public boolean updatePublisher(Publisher publisher) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[2];
            values[0] =publisher.getId();
            values[1] =publisher.getName();
            
            boolean isUpdated = dbH.update(values);
            if (isUpdated) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }
    
    
    public ArrayList<Publisher> fetchAll() throws SQLException {
        ArrayList<Publisher> publishers = new ArrayList<>();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchAll();
            while (results.next()) {
                Publisher p = new Publisher();
                p.setId(results.getString("publisher_Id"));
                p.setName(results.getString("publisher_Name"));
                
                publishers.add(p);
            }
            return publishers;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
         return null;
    }
}
