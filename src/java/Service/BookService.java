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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;

/**
 *
 * @book WorldEdit
 */
public class BookService extends Book {

    BookModal bookModal = new BookModal();

    public boolean check(Book book) throws SQLException {
        if (book.valid("insert")) {
            System.out.println("Inserting");
            if (!bookModal.insertBook(book)) {

                String[] values = new String[2];
                values[0] = bookModal.getMaxId();

                for (String item : book.getGenres()) {
                    values[1] = item;
                    bookModal.insertGenres(values);
                }

                for (String item : book.getAuthors()) {
                    values[1] = item;
                    bookModal.insertAuthors(values);
                }
                return true;
            } else {
                return false;
            }
        } else if (book.valid("update")) {
            if (bookModal.updateBook(book)) {
                return true;
            } else {
                return false;
            }
        } else if (book.valid("delete")) {
            System.out.println("Delete from Service");

            if (bookModal.deleteAuthors(book) && bookModal.deleteGenres(book)) {
                if (bookModal.deleteBook(book)) {
                    return true;
                } else {
                    return false;
                }
            }

        }
        return false;
    }

    public String getData() throws SQLException {
        ArrayList<Book> books;
        try {
            books = bookModal.fetchAll();
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (int i = 0; i < books.size(); i++) {
                JsonObject item = new JsonObject();
                item.addProperty("id", books.get(i).getId());
                item.addProperty("name", books.get(i).getName());
                item.addProperty("isbn", books.get(i).getISBN());
                item.addProperty("hash", books.get(i).getHash());
                item.addProperty("publisher", books.get(i).getPublisher());
                item.addProperty("available", books.get(i).isAvailable());
                item.addProperty("total", books.get(i).getTotal());
                item.addProperty("qty", books.get(i).getQuantity());
                item.addProperty("return", books.get(i).isReqReturn());
                item.addProperty("image", books.get(i).getImage());
                item.addProperty("pdf", books.get(i).getPdf());

                arr.add(item);
            }
            gObj.add("datas", arr);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getSpecificData(String id) throws SQLException {

        Book book = bookModal.fetchSpecific(id);
        com.google.gson.JsonObject gObj = new JsonObject();

        JsonArray arrMain = new JsonArray();
        arrMain.add(Boolean.TRUE);

        JsonObject item = new JsonObject();
        item.addProperty("id", book.getId());
        item.addProperty("name", book.getName());
        item.addProperty("isbn", book.getISBN());
        item.addProperty("hash", book.getHash());
        item.addProperty("publisher", book.getPublisher());
        item.addProperty("available", book.isAvailable());
        item.addProperty("total", book.getTotal());
        item.addProperty("qty", book.getQuantity());
        item.addProperty("return", book.isReqReturn());
        item.addProperty("image", book.getImage());
        item.addProperty("pdf", book.getPdf());

        JsonObject item2 = new JsonObject();
        for (int i = 0; i < book.getAuthors().size(); i++) {
            String temp = book.getAuthors().get(i);
            item2.addProperty("author" + temp, temp);
        }
        item.add("author", item2);

        JsonObject item3 = new JsonObject();
        for (int i = 0; i < book.getGenres().size(); i++) {
            String temp = book.getGenres().get(i);
            item3.addProperty("genre" + temp, temp);
        }
        item.add("genre", item3);
        arrMain.add(item);

        gObj.add("datas", item);
        return gObj.toString();
    }
}
