/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Modal.BookModal;
import ObjClasses.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class BorrowedBooksService {
    BookModal bookModal = new BookModal();
    
    public String getData(String id) throws SQLException {
        ArrayList<Book> books;
        try {
            books = bookModal.fetchBorrowedBooks(id);
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < books.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", books.get(i).getId());
                item.addProperty("name", books.get(i).getName());
                item.addProperty("isbn", books.get(i).getISBN());
                item.addProperty("return", books.get(i).isReqReturn());
                item.addProperty("image", books.get(i).getImage());
                item.addProperty("pdf", books.get(i).getPdf());
                item.addProperty("issue_date", books.get(i).getIssue_date());
                item.addProperty("due_date", books.get(i).getDue_date());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }
    
    
    public String getHistory(String id) throws SQLException {
        ArrayList<Book> books;
        try {
            books = bookModal.fetchBorrowedBooks(id);
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < books.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", books.get(i).getId());
                item.addProperty("name", books.get(i).getName());
                item.addProperty("isbn", books.get(i).getISBN());
                item.addProperty("return", books.get(i).isReqReturn());
                item.addProperty("image", books.get(i).getImage());
                item.addProperty("pdf", books.get(i).getPdf());
                item.addProperty("issue_date", books.get(i).getIssue_date());
                item.addProperty("due_date", books.get(i).getDue_date());
                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }
}
