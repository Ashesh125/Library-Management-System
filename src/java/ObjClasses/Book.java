/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjClasses;

import static Utilities.NumberCheck.isNumeric;
import java.util.ArrayList;
import Exceptions.InvalidISBNException;

/**
 *
 * @author WorldEdit
 */
public class Book extends SingleValueTargets{
    
    private String ISBN;
    private String Publisher;

    private ArrayList<String> authors;
    private ArrayList<String> genres;
    private String hash;
    private boolean available;
    private int quantity;
    private int total;
    private String due_date;
    private String issue_date;
    private String pdf;
    private String image;
    private boolean reqReturn;
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        try{
            if(!isNumeric(ISBN) && !(ISBN.length() == 10 || ISBN.length() == 13)){            
                throw new InvalidISBNException(); 
            }else{
                this.ISBN = ISBN;
            }
        }catch(InvalidISBNException e){
            this.ISBN = "0000000000000";
        }
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }
    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isReqReturn() {
        return reqReturn;
    }

    public void setReqReturn(boolean reqReturn) {
        this.reqReturn = reqReturn;
    }
    
    
    
}
