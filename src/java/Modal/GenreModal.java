/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import ObjClasses.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @genre WorldEdit
 */
public class GenreModal {
    DatabaseHelper dbH;
    String table_name = "genres";
    
    public boolean insertGenre(Genre genre) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[1];
            values[0] =genre.getName();
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
    
    public boolean updateGenre(Genre genre) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[2];
            values[0] =genre.getId();
            values[1] =genre.getName();
            
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
    
    
    public boolean deleteGenre(Genre genre) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            int isDeleted = dbH.delete(genre.getId());
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
    
    public ArrayList<Genre> fetchAll() throws SQLException {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchAll();
            while (results.next()) {
                Genre g = new Genre();
                g.setId(results.getString("genre_Id"));
                g.setName(results.getString("genre_Name"));
                
                genres.add(g);
            }
            return genres;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
               dbH.closeConnection();
        }
         return null;
    }
}
