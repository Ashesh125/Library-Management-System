/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Service.PatronService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WorldEdit
 */
public class BorrowerController extends HttpServlet {

    PatronService patronService = new PatronService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String type = req.getParameter("type");
        
        HttpSession ses = req.getSession();
       
        if(type.equals("")){
            resp.sendRedirect(req.getContextPath()+"/Forms/Logout");
        }else if(type.equals("sp")){
            type = ses.getAttribute("patron_Id").toString();
        }
        resp.getWriter().print(patronService.getBorrowers(type));
    }
}
