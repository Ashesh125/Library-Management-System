/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import Configuration.LibraryHelper;
import ObjClasses.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @admin WorldEdit
 */
public class AdminModal {
    DatabaseHelper dbH;
    String table_name = "admin";
    
    public boolean insertAdmin(Admin admin) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            String values[] = new String[3];
            values[0] = admin.getName();
            values[1] = admin.getHash();
            values[2] = admin.getHash2();
            values[3] = admin.getRoll();
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
    
    public boolean updateAdmin(Admin admin) throws SQLException {
        boolean changePswd = false;
        try {
            LibraryHelper dbHL = new LibraryHelper(table_name);
            ArrayList<String> values = new ArrayList();
            values.add(admin.getId());
            
            values.add(admin.getName());
            values.add(admin.getRoll());
            
            if(!admin.getHash2().equals("")){
                values.add(admin.getHash2());
                changePswd = true;
            }
            boolean isUpdated = dbHL.updateAdmin(values,changePswd);
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
    
    
    public boolean deleteAdmin(Admin admin) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            
            int isDeleted = dbH.delete(admin.getId());
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
    
    public ArrayList<Admin> fetchAll() throws SQLException {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchAll();
            while (results.next()) {
                Admin a = new Admin();
                a.setId(results.getString("admin_Id"));
                a.setName(results.getString("admin_Name"));
                a.setHash(results.getString("admin_Hash"));
                a.setRoll(results.getString("admin_Roll"));
                admins.add(a);
            }
            return admins;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
               dbH.closeConnection();
        }
         return null;
    }
}
