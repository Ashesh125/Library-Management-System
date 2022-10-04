/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Exceptions.EmptyValueException;
import Exceptions.ExceedsTotalException;
import Modal.BookIssueModal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author WorldEdit
 */
public class BookIssueService {
    
    BookIssueModal issueModal = new BookIssueModal();
    
    public void action(String values[]) throws EmptyValueException, SQLException, ClassNotFoundException, ExceedsTotalException {
        
        for (String value : values) {
            if (value.isEmpty()) {
                throw new EmptyValueException("Something");
            }
        }
        
        String[] books = values[1].split("[,]", 0);
        ArrayList<String> returnBooks = new ArrayList(Arrays.asList(books));
        ArrayList<String> issueBooks = checkIssue(values[0], books);
        
        for (String b : issueBooks) {
            if (returnBooks.contains(b)) {
                returnBooks.remove(b);
            }
        }
        
            System.out.println(returnBooks);
            System.out.println(issueBooks);
        if (returnBooks.size() > 0) {
            
            returnBooks = exceedsQty(returnBooks, "return");
            
            System.out.println(returnBooks);
            issueModal.returned(values[0], returnBooks);
            issueModal.decreaseAmount(values[0], returnBooks.size());
        }
        if (issueBooks.size() > 0) {
            issueBooks = exceedsQty(issueBooks, "issue");
            System.out.println(issueBooks);
            
            issueModal.issue(values[0], values[2], issueBooks);
            issueModal.increaseAmount(values[0], issueBooks.size());
        }
        
    }
    
    public ArrayList<String> checkIssue(String patron, String[] books) throws SQLException, ClassNotFoundException {
        ArrayList<String> issueBooks = new ArrayList();
        for (String book : books) {
            if (!issueModal.checkTaken(patron, book)) {
                issueBooks.add(book);
            }
        }
        return issueBooks;
    }
    
    private ArrayList<String> exceedsQty(ArrayList<String> books, String type) throws SQLException, ClassNotFoundException, ExceedsTotalException {
        ArrayList<String> temp = new ArrayList();
        
        if (type.equals("issue")) {
            for (String book : books) {
                int qty = issueModal.getBookQty(book);
                if (qty <= 0) {
                    throw new ExceedsTotalException();
                } else {
                    if (qty == 1) {
                        issueModal.setBookAvailable(book, false);
                    }
                    issueModal.decBookQty(book);
                    temp.add(book);
                }
            }
        } else if (type.equals("return")) {
            for (String book : books) {
                int total = issueModal.getBookTotal(book);
                int qty = issueModal.getBookQty(book);
                System.out.println(total +"   "+ qty);
                if (qty >= total) {
                    throw new ExceedsTotalException();
                } else {
                    if (qty == 0) {
                        issueModal.setBookAvailable(book, true);
                    }
                    issueModal.incBookQty(book);
                    temp.add(book);
                }
            }
        }
        
        return temp;
    }
    
}
