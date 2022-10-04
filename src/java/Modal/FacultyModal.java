/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import ObjClasses.Faculty;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class FacultyModal {
     DatabaseHelper dbH;
    String table_name = "facultys";
    
    public boolean insertFaculty(Faculty faculty) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[1];
            values[0] =faculty.getName();
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
    
    public boolean updateFaculty(Faculty faculty) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[2];
            values[0] =faculty.getId();
            values[1] =faculty.getName();
            
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
    
    
    public boolean deleteFaculty(Faculty faculty) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            int isDeleted = dbH.delete(faculty.getId());
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
    
    public ArrayList<Faculty> fetchAll() throws SQLException {
        ArrayList<Faculty> facultys = new ArrayList<>();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchAll();
            while (results.next()) {
                Faculty g = new Faculty();
                g.setId(results.getString("faculty_Id"));
                g.setName(results.getString("faculty_Name"));
                
                facultys.add(g);
            }
            return facultys;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
               dbH.closeConnection();
        }
         return null;
    }
}
