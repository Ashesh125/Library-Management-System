/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import FormHandler.AuthorController;
import Modal.AuthorModal;
import ObjClasses.Author;
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
public class AuthorService extends Author {

    AuthorModal authorModal = new AuthorModal();
    Author author = new Author();
    
    public boolean check(String[] data) throws SQLException, EmptyValueException {
        
        //putting value in objects
        if(data[0].equals("")){
            throw new EmptyValueException("Author Id");
        }else{
            author.setId(data[0]);
        }
        
       
            author.setName(data[1]);
        
        
        //determining which operation to perform
        if (author.valid("insert")) {
            if (authorModal.insertAuthor(author)) {
                return true;
            }
        } else if (author.valid("update")) {
            if (authorModal.updateAuthor(author)) {
                return true;
            }
        } else if (author.valid("delete")) {
            if (authorModal.deleteAuthor(author)) {
                return true;
            }
        }
        return false;
    }

    public String getData() {
        ArrayList<Author> authors;
        try {
            authors = authorModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < authors.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", authors.get(i).getId());
                item.addProperty("name", authors.get(i).getName());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
