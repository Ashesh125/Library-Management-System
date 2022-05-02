/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Exceptions.EmptyValueException;
import Exceptions.ExceedsTotalException;
import Service.BookIssueService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WorldEdit
 */
public class BookIssue extends HttpServlet {

    BookIssueService bookIssue = new BookIssueService();
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String values[] = new String[3];

        HttpSession ses = req.getSession();
        
        values[0] = req.getParameter("patron_Id");
        values[1] = req.getParameter("books");
        values[2] = (String) ses.getAttribute("admin_Id");
        
        try {
            bookIssue.action(values);
        } catch (EmptyValueException ex) {
            Logger.getLogger(BookIssue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookIssue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookIssue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExceedsTotalException ex) {
            Logger.getLogger(BookIssue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
