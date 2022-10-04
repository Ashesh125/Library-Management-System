/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjClasses;

import Exceptions.EmptyValueException;

/**
 *
 * @author WorldEdit
 */
public class User extends SingleValueTargets{
    protected String hash;
    protected String password;
    protected String roll;
    protected String hash2;

    public String getHash2() {
        return hash2;
    }

    public void setHash2(String hash2) {
        this.hash2 = hash2;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
    
    
    protected boolean validateUser(String roll,String password) throws EmptyValueException{
        if(roll.equals("")){
            throw new EmptyValueException("Roll no cannot be empty");
        }else if(password.equals("")){
            throw new EmptyValueException("Password no cannot be empty");
        }else if(roll.length() != 6){
            throw new EmptyValueException("Roll no length is Incorrect");
        }else if (password.length() < 6 || password.length() > 10){
            throw new EmptyValueException("Password length invalid");
        }else {
            return true;
        }
    }
}
