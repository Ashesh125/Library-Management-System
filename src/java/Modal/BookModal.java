/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import Configuration.LibraryHelper;
import ObjClasses.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @book WorldEdit
 */
public class BookModal {

    DatabaseHelper dbH;
    String table_name = "books";

    public boolean insertBook(Book book) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);

            String values[] = new String[10];
            
            values[0] = book.getName();
            values[1] = book.getISBN();
            values[2] = book.getHash();
            values[3] = book.getPublisher();
            if (book.isAvailable()) {
                values[4] = "1";
            } else {
                values[4] = "0";
            }
            Integer temp = book.getTotal();
            values[5] = temp.toString();
            values[6] = temp.toString();
            
            if (book.isReqReturn()) {
                values[7] = "1";
            } else {
                values[7] = "0";
            }

            values[8] = book.getImage();
            values[9] = book.getPdf();
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

    public boolean updateBook(Book book) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);

            String values[] = new String[10];
            values[0] = book.getId();
            values[1] = book.getName();
            values[2] = book.getISBN();
            values[3] = book.getHash();
            values[4] = book.getPublisher();
            if (book.isAvailable()) {
                values[5] = "1";
            } else {
                values[5] = "0";
            }
            Integer temp = book.getQuantity();
            values[6] = temp.toString();

            if (book.isReqReturn()) {
                values[7] = "1";
            } else {
                values[7] = "0";
            }

            values[8] = book.getImage();
            values[9] = book.getPdf();
            boolean isUpdated = dbH.update(values);
            if (isUpdated) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } finally {
            dbH.closeConnection();
        }
        return false;
    }

    public boolean deleteBook(Book book) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            System.out.println("Delet called!");
            int isDeleted = dbH.delete(book.getId());
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

    public boolean deleteAuthors(Book book) throws SQLException {
        try {
            dbH = new DatabaseHelper("author_group");
            System.out.println("Delet called!");
            int isDeleted = dbH.deleteById("book_Id",book.getId());
            if (isDeleted > -1 ) {
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
    
    public boolean deleteGenres(Book book) throws SQLException {
        try {
            dbH = new DatabaseHelper("genre_group");
            System.out.println("Delet called!");
             int isDeleted = dbH.deleteById("book_Id",book.getId());
            if (isDeleted > -1) {
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
    
    
    public String getMaxId() throws SQLException{
        try {
            dbH = new DatabaseHelper("books");
            ResultSet results = dbH.getMaxId();
            while (results.next()) {
                return results.getString("max");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
        return "0";

    }
    
    public boolean insertGenres(String[] values){
        try {
            dbH = new DatabaseHelper("genre_group");
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
    
    
    public boolean insertAuthors(String[] values){
        try {
            dbH = new DatabaseHelper("author_group");
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
    
    public ArrayList<Book> fetchAll() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        try {
            LibraryHelper dbHL = new LibraryHelper("books");
//            dbH = new DatabaseHelper("books");
            ResultSet results = dbHL.fetchAllBooks();
            while (results.next()) {
                Book a = new Book();
                a.setId(results.getString("books_Id"));
                a.setName(results.getString("book_Name"));
                a.setISBN(results.getString("book_ISBN"));
                a.setHash(results.getString("book_Hash"));
                a.setPublisher(results.getString("publisher_Name"));
                a.setAvailable(results.getBoolean("book_Available"));
                a.setTotal(results.getInt("book_Total"));
                a.setQuantity(results.getInt("book_Quantity"));
                a.setReqReturn(results.getBoolean("book_Return"));
                a.setImage(results.getString("book_Image"));
                a.setPdf(results.getString("book_PDF"));

                books.add(a);
            }
            return books;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            
        }
        return null;
    }

    public Book fetchSpecific(String id) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchById(id);
            Book a = new Book();
            while (results.next()) {
                a.setId(results.getString("books_Id"));
                a.setName(results.getString("book_Name"));
                a.setISBN(results.getString("book_ISBN"));
                a.setHash(results.getString("book_Hash"));
                a.setPublisher(results.getString("book_Publisher"));
                a.setAvailable(results.getBoolean("book_Available"));
                a.setTotal(results.getInt("book_Total"));
                a.setQuantity(results.getInt("book_Quantity"));
                a.setReqReturn(results.getBoolean("book_Return"));
                a.setImage(results.getString("book_Image"));
                a.setPdf(results.getString("book_PDF"));

            }
            
            ResultSet authors = dbH.fetchBlocks("author_group", "book_Id", id);
            ArrayList<String> aut = new ArrayList();
            while (authors.next()) {
                aut.add(authors.getString("author_Id"));
            }
            a.setAuthors(aut);
            
            ResultSet genres = dbH.fetchBlocks("genre_group", "book_Id", id);
            ArrayList<String> gen = new ArrayList();
            while (genres.next()) {
                gen.add(genres.getString("genre_Id"));
            }
            a.setGenres(gen);
            
            return a;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
        return null;
    }
}
