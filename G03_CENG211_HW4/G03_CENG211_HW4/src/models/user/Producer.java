package models.user;

import java.util.List;

/**
 * Producer class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Producer extends User {
    /**
     * Instantiates a new Producer.
     *
     * @param userID        the user id
     * @param username      the username
     * @param password      the password
     * @param displayedName the displayed name
     */
    public Producer(String userID, String username, String password, String displayedName) {
        super(userID, username, password, displayedName);
    }

    /**
     * Instantiates a new Producer.
     *
     * @param data the data
     */
    public Producer(List<String> data) {
        super(data);
    }

    public String toCSV(){
        return getUserID() + ";" +
                this.username + ";" +
                this.password + ";" +
                getDisplayedName() +";"+
                "producer";
    }
}
