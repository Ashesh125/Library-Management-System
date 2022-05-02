/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import ObjClasses.Admin;
import ObjClasses.Patron;
import ObjClasses.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author WorldEdit
 */
public class LoginModal {

    DatabaseHelper dbH;
    String table_name;

    public boolean adminExists(User user) throws SQLException, ClassNotFoundException {
        table_name = "admin";
        dbH = new DatabaseHelper(table_name);
        String id = dbH.checkHashExists(user.getHash());
        if (id.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public Admin checkAdmin(User user) throws SQLException, ClassNotFoundException {
        table_name = "admin";
        dbH = new DatabaseHelper(table_name);
        String id = dbH.checkHashExists(user.getHash());

        try {
            ResultSet results = dbH.fetchById(id);
            Admin a = new Admin();
            while (results.next()) {
                a.setId(results.getString("admin_Id"));
                a.setName(results.getString("admin_Name"));
                a.setRoll(results.getString("admin_Roll"));
                a.setHash(results.getString("admin_Hash"));
            }

            return a;

        } catch (SQLException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
        return null;

    }
    
    public boolean patronExists(User user) throws SQLException, ClassNotFoundException {
        table_name = "patron";
        dbH = new DatabaseHelper(table_name);
        String id = dbH.checkHashExists(user.getHash());
        if (id.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public Patron checkPatron(User user) throws SQLException, ClassNotFoundException {
        table_name = "patron";
        dbH = new DatabaseHelper(table_name);
        String id = dbH.checkHashExists(user.getHash());

        try {
            ResultSet results = dbH.fetchById(id);
            Patron a = new Patron();
            while (results.next()) {
                a.setId(results.getString("patron_Id"));
                a.setName(results.getString("patron_Name"));
                a.setRoll(results.getString("patron_Roll"));
                a.setHash(results.getString("patron_Hash"));
            }

            return a;

        } catch (SQLException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
        return null;

    }
}
