/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Exceptions.EmptyValueException;
import Service.BorrowedBooksService;
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
public class BorrowedBooksController extends HttpServlet {
    
    BorrowedBooksService bbService = new BorrowedBooksService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        HttpSession ses = req.getSession();
        String id = ses.getAttribute("patron_Id").toString();
        if(id.equals("")){
            resp.sendRedirect(req.getContextPath()+"/Forms/Logout");
        }   
        try {
            resp.getWriter().print(bbService.getData(id));
        } catch (SQLException ex) {
            Logger.getLogger(BorrowedBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

