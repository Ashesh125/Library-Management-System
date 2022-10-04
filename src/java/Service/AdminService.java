/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import FormHandler.AdminController;
import Modal.AdminModal;
import ObjClasses.Admin;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utilities.Hash;
/**
 *
 * @admin WorldEdit
 */
public class AdminService extends Admin {

    AdminModal adminModal = new AdminModal();
    Admin admin = new Admin();
    
    public boolean check(String[] data) throws SQLException, EmptyValueException {
        
        //putting value in objects
        if(data[0].equals("")){
            throw new EmptyValueException("Admin Id");
        }else{
            admin.setId(data[0]);
        }
        
     
            admin.setName(data[1]);
        String hash = data[2].equals("")?"" :Utilities.Hash.getHash(data[3]);
        String hash2 = data[2].equals("")?"" :Utilities.Hash.getHash(data[3] + data[2]);
        admin.setHash(hash);
        admin.setHash2(hash2);
        admin.setRoll(data[3]);
        
        //determining which operation to perform
        if (admin.valid("insert")) {
            if (adminModal.insertAdmin(admin)) {
                return true;
            }
        } else if (admin.valid("update")) {
            if (adminModal.updateAdmin(admin)) {
                return true;
            }
        } else if (admin.valid("delete")) {
            if (adminModal.deleteAdmin(admin)) {
                return true;
            }
        }
        return false;
    }

    public String getData() {
        ArrayList<Admin> admins;
        try {
            admins = adminModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < admins.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", admins.get(i).getId());
                item.addProperty("name", admins.get(i).getName());
                item.addProperty("roll", admins.get(i).getRoll());
                item.addProperty("hash", admins.get(i).getHash());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}