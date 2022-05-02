/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import Modal.LoginModal;
import ObjClasses.User;
import ObjClasses.Admin;
import ObjClasses.Patron;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
/**
 *
 * @author WorldEdit
 */
public class LoginService extends User {

    User user = new User();
    LoginModal loginModal = new LoginModal();
    public String check(String[] values,HttpSession session) throws EmptyValueException, SQLException, ClassNotFoundException{
        //values[0] = roll no
        //values[1] = password 
        if(super.validateUser(values[0], values[1])){
            user.setRoll(values[0]);
            user.setPassword(values[1]);
            user.setHash(Utilities.Hash.getHash(values[0]+values[1]));
            
            if(loginModal.adminExists(user)){
                Admin a = loginModal.checkAdmin(user);
                
                session.setAttribute("admin_Id", a.getId());
                session.setAttribute("admin_Name", a.getName());
                return "admin";
            }else if(loginModal.patronExists(user)){
                Patron p = loginModal.checkPatron(user);
                session.setAttribute("patron_Id", p.getId());
                session.setAttribute("patron_Name", p.getName());
                return "patron";
            }else{
                return "error";
            }
            
        
        }
        return "error";
    } 
    
    
    
}