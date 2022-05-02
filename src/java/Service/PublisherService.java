/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import FormHandler.PublisherController;
import Modal.PublisherModal;
import ObjClasses.Publisher;
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
public class PublisherService extends Publisher {

    PublisherModal publisherModal = new PublisherModal();
    Publisher publisher = new Publisher();
 
    public boolean check(String[] data) throws SQLException, EmptyValueException {
        //putting value in objects
        if(data[0].equals("")){
            throw new EmptyValueException("Publisher Id");
        }else{
            publisher.setId(data[0]);
        }
      
            publisher.setName(data[1]);
        
        
        //determining which operation to perform
        if (publisher.valid("insert")) {
            if (publisherModal.insertPublisher(publisher)) {
                return true;
            }
        } else if (publisher.valid("update")) {
            if (publisherModal.updatePublisher(publisher)) {
                return true;
            }
        } else if (publisher.valid("delete")) {
            if (publisherModal.deletePublisher(publisher)) {
                return true;
            }
        }
        return false;
    }

    public String getData() {
        ArrayList<Publisher> publishers;
        try {
            publishers = publisherModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < publishers.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", publishers.get(i).getId());
                item.addProperty("name", publishers.get(i).getName());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }
    
}
