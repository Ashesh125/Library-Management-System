/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import Modal.PatronModal;
import ObjClasses.Patron;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class PatronService extends Patron {

    PatronModal patronModal = new PatronModal();
    Patron patron = new Patron();

    public boolean check(String[] data) throws SQLException, EmptyValueException {

        //putting value in objects
        if (data[0].equals("")) {
            throw new EmptyValueException("Patron Id");
        } else {
            patron.setId(data[0]);
        }
        patron.setName(data[1]);
        patron.setRoll(data[2]);
        patron.setEmail(data[3]);
        patron.setPhone(data[4]);
        String hash = data[5].equals("")?"" :Utilities.Hash.getHash(data[2] + data[5]);
        String hash2 = data[5].equals("")?"" :Utilities.Hash.getHash(data[2] + data[5]);
        patron.setHash(hash);
        patron.setHash2(hash2);

        patron.setFaculty(data[7]);
        patron.setImage("image");
        patron.setQty(data[9]);
        patron.setFine(Integer.parseInt(data[10]));

        System.out.println(data[11]);
        if (data[11] == null) {
            patron.setFlagged(false);
        } else {
            patron.setFlagged(true);
        }

        //determining which operation to perform
        if (patron.valid("insert")) {
            if (patronModal.insertPatron(patron)) {
                return true;
            }
        } else if (patron.valid("update")) {
            if (patronModal.updatePatron(patron)) {
                return true;
            }
        } else if (patron.valid("delete")) {
            if (patronModal.deletePatron(patron)) {
                return true;
            }
        }
        return false;
    }

    public String getData() {
        ArrayList<Patron> patrons;
        try {
            patrons = patronModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < patrons.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", patrons.get(i).getId());
                item.addProperty("name", patrons.get(i).getName());
                item.addProperty("roll", patrons.get(i).getRoll());
                item.addProperty("email", patrons.get(i).getEmail());
                item.addProperty("phone", patrons.get(i).getPhone());
                item.addProperty("hash", patrons.get(i).getHash());
                item.addProperty("faculty", patrons.get(i).getFaculty());
                item.addProperty("image", patrons.get(i).getImage());
                item.addProperty("qty", patrons.get(i).getQty());
                item.addProperty("fine", patrons.get(i).getFine());
                item.addProperty("flagged", patrons.get(i).isFlagged());

                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getSpecificData(String hash) {
        Patron patron;
        try {
            patron = patronModal.fetchSpecific("patron_Hash", hash);

            com.google.gson.JsonObject gObj = new JsonObject();

            JsonObject item = new JsonObject();
            item.addProperty("id", patron.getId());
            item.addProperty("name", patron.getName());
            item.addProperty("roll", patron.getRoll());
            item.addProperty("email", patron.getEmail());
            item.addProperty("phone", patron.getPhone());
            item.addProperty("hash", patron.getHash());
            item.addProperty("faculty", patron.getFaculty());
            item.addProperty("image", patron.getImage());
            item.addProperty("qty", patron.getQty());
            item.addProperty("fine", patron.getFine());
            item.addProperty("flagged", patron.isFlagged());

            gObj.add("user", item);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getSpecificDataByHash(String hash) {
        Patron patron;
        try {
            patron = patronModal.fetchByHash(hash);

            com.google.gson.JsonObject gObj = new JsonObject();

            JsonObject item = new JsonObject();
            item.addProperty("id", patron.getId());
            item.addProperty("name", patron.getName());
            item.addProperty("roll", patron.getRoll());
            item.addProperty("email", patron.getEmail());
            item.addProperty("phone", patron.getPhone());
            item.addProperty("hash", patron.getHash());
            item.addProperty("faculty", patron.getFaculty());
            item.addProperty("image", patron.getImage());
            item.addProperty("qty", patron.getQty());
            item.addProperty("fine", patron.getFine());
            item.addProperty("flagged", patron.isFlagged());

            gObj.add("user", item);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getBorrowers(String type) {
        ArrayList<String[]> patrons = new ArrayList();
        try {
            patrons = patronModal.fetchAllBorrowers(type);
            if(patrons != null){
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            
            for (int i = 0; i < patrons.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("borrower_Id", patrons.get(i)[0]);
                item.addProperty("patron_Id", patrons.get(i)[1]);
                
                item.addProperty("book_Id", patrons.get(i)[2]);
                item.addProperty("admin_Id", patrons.get(i)[3]);
                item.addProperty("borrowed_Date", patrons.get(i)[4]);
                
                item.addProperty("due_Date", patrons.get(i)[5]);
                item.addProperty("returned", patrons.get(i)[6]);
                item.addProperty("patron_Name", patrons.get(i)[7]);
                item.addProperty("admin_Name", patrons.get(i)[8]);
                item.addProperty("book_Name", patrons.get(i)[9]);
                
                
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
            }
        } catch (SQLException ex) {
        }
        
        return null;
    }

}
