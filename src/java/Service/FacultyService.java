/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import FormHandler.FacultyController;
import Modal.FacultyModal;
import ObjClasses.Faculty;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WorldEdit
 */
public class FacultyService{
    Faculty faculty = new Faculty();
    FacultyModal facultyModal = new FacultyModal();

    public boolean check(String[] data) throws SQLException, EmptyValueException {
        
        //putting value in objects
        if(data[0].equals("")){
            throw new EmptyValueException("Faculty Id");
        }else{
            faculty.setId(data[0]);
        }
        
      
            faculty.setName(data[1]);
        
        
        //determining which operation to perform
        
        if (faculty.valid("insert")) {
            if (facultyModal.insertFaculty(faculty)) {
                return true;
            }
        } else if (faculty.valid("update")) {
            if (facultyModal.updateFaculty(faculty)) {
                return true;
            }
        } else if (faculty.valid("delete")) {
            if (facultyModal.deleteFaculty(faculty)) {
                return true;
            }
        }
        return false;
    }

    public String getData() {
        ArrayList<Faculty> facultys;
        try {
            facultys = facultyModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < facultys.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", facultys.get(i).getId());
                item.addProperty("name", facultys.get(i).getName());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
