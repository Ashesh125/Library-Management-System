package FormHandler;

import Exceptions.EmptyValueException;
import ObjClasses.Author;
import Service.AuthorService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorController extends HttpServlet {
    
    AuthorService authorService = new AuthorService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] data = new String[2];
        data[0] = req.getParameter("id");
        data[1] = req.getParameter("name");
        try {
            if(!authorService.check(data)){
                resp.sendRedirect(req.getContextPath() + "/Resources/Pages/Author_List.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyValueException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

       
        resp.getWriter().print(authorService.getData());

    }

}
