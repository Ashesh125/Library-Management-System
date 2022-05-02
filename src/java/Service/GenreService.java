/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import FormHandler.GenreController;
import Modal.GenreModal;
import ObjClasses.Genre;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @genre WorldEdit
 */
public class GenreService extends Genre {

    GenreModal genreModal = new GenreModal();
    Genre genre = new Genre();
    
    public boolean check(String[] data) throws SQLException, EmptyValueException {
        
        //putting value in objects
        
        if(data[0].equals("")){
            throw new EmptyValueException("Genre Id");
        }else{
            genre.setId(data[0]);
        }
        
            genre.setName(data[1]);
        
        
        //determining which operation to perform
        
        if (genre.valid("insert")) {
            if (genreModal.insertGenre(genre)) {
                return true;
            }
        } else if (genre.valid("update")) {
            if (genreModal.updateGenre(genre)) {
                return true;
            }
        } else if (genre.valid("delete")) {
            if (genreModal.deleteGenre(genre)) {
                return true;
            }
        }
        return false;
    }

    public String getData() {
        ArrayList<Genre> genres;
        try {
            genres = genreModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < genres.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", genres.get(i).getId());
                item.addProperty("name", genres.get(i).getName());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
            Logger.getLogger(GenreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
