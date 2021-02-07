package models.user;

import java.util.List;

/**
 * Customer class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Customer extends User {
    /**
     * Instantiates a new Customer.
     *
     * @param userID        the user id
     * @param username      the username
     * @param password      the password
     * @param displayedName the displayed name
     */
    public Customer(String userID, String username, String password, String displayedName) {
        super(userID, username, password, displayedName);
    }

    /**
     * Instantiates a new Customer.
     *
     * @param data the data
     */
    public Customer(List<String> data) {
        super(data);
    }

    public String toCSV(){
        return getUserID() + ";" +
                this.username + ";" +
                this.password + ";" +
                getDisplayedName() +";"+
                "customer";
    }
}
