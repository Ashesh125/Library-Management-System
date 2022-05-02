/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import ObjClasses.Book;
import Service.BookService;
import static Utilities.Hash.getHash;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WorldEdit
 */
public class BookDetailController extends HttpServlet {

    BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, RuntimeException {
        Book book = new Book();
        
                book.setId(req.getParameter("id"));
        if(req.getParameter("name").equals("")){
            try {
                book.setName("");
                if (!bookService.check(book)) {
                    resp.getWriter().print("\nSubmitted\n");
                    resp.sendRedirect(req.getContextPath() + "/Resources/Pages/Book_List.jsp");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String isbn = req.getParameter("isbn");
        String bookHash = getHash(isbn);
        
        ArrayList<String> genres = new ArrayList();
        ArrayList<String> authors = new ArrayList();
        
        Enumeration<String> e = req.getParameterNames();

        if (e.hasMoreElements()) {
            do {
                String ele = e.nextElement().toString();

                if (ele.contains("genre")) {
                    resp.getWriter().print(seperateId(ele) + "\t");
                    genres.add(seperateId(ele));

                } else if (ele.contains("author")) {
                    resp.getWriter().print(seperateId(ele) + "\t");
                    authors.add(seperateId(ele));

                }
                resp.getWriter().print(ele + "\t");
                resp.getWriter().print(req.getParameter(ele) + "\n");
            } while (e.hasMoreElements());
        }
        resp.getWriter().print("Hash\t");
        resp.getWriter().print(bookHash + "\n");

        book.setId(req.getParameter("id"));
        book.setName(req.getParameter("name"));
        book.setISBN(isbn);
        book.setHash(bookHash);
        book.setPublisher(req.getParameter("publisher"));
        book.setTotal(Integer.parseInt(req.getParameter("qty")));
        book.setQuantity(0);
        

        if (req.getParameter("available") != null) {
            book.setAvailable(true);
        } else {
            book.setAvailable(false);

        }
        if (req.getParameter("return") != null) {
            book.setReqReturn(true);
        } else {
            book.setReqReturn(false);

        }
        book.setGenres(genres);
        book.setAuthors(authors);
        book.setImage("Asd");
        book.setPdf("Asd");
        for (String item : book.getGenres()) {
            resp.getWriter().print(item + "\t");
        }
        
        try {
//            System.out.println("This is sout");
//            resp.getWriter().print("happens " +bookService.check(book));    
//            System.out.println("This is Whahjkdshjkd");
            
            if (!bookService.check(book)) {
                resp.getWriter().print("\nSubmitted\n");
                resp.sendRedirect(req.getContextPath() + "/Resources/Pages/Book_List.jsp");
            }
        } catch (SQLException ex) {
            System.out.println("This is sout" + ex.getMessage());
            resp.getWriter().print(ex);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            resp.getWriter().print(ex);
        }
    }

    public String seperateId(String input) {
        final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");

        Matcher matcher = lastIntPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        try {
//            resp.getWriter().print(bookService.getSpecificData("1"));
            resp.getWriter().print(bookService.getSpecificData(req.getParameter("id")));
        } catch (SQLException ex) {
            Logger.getLogger(BookDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
