/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import ObjClasses.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class AuthorModal {
    DatabaseHelper dbH;
    String table_name = "authors";
    
    public boolean insertAuthor(Author author) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[1];
            values[0] =author.getName();
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
    
    public boolean updateAuthor(Author author) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[2];
            values[0] =author.getId();
            values[1] =author.getName();
            
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
    
    
    public boolean deleteAuthor(Author author) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            int isDeleted = dbH.delete(author.getId());
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
    
    public ArrayList<Author> fetchAll() throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchAll();
            while (results.next()) {
                Author a = new Author();
                a.setId(results.getString("author_Id"));
                a.setName(results.getString("author_Name"));
                
                authors.add(a);
            }
            return authors;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
               dbH.closeConnection();
        }
         return null;
    }
}
