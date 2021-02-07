package models.user;

import java.util.List;


/**
 * Abstract user class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public abstract class User {
    private String userID;
    protected String username;
    protected String password;
    private String displayedName;

    /**
     * Instantiates a new User.
     *
     * @param userID        the user id
     * @param username      the username
     * @param password      the password
     * @param displayedName the displayed name
     */
    public User(String userID,
                String username,
                String password,
                String displayedName) {

        this.userID = userID;
        this.username = username;
        this.password = password;
        this.displayedName = displayedName;
    }

    /**
     * Instantiates a new User.
     *
     * @param data the data
     */
    public User(List<String> data) {
        this(data.get(0), data.get(1), data.get(2), data.get(3));
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets displayed name.
     *
     * @return the displayed name
     */
    public String getDisplayedName() {
        return displayedName;
    }

    /**
     * Sets displayed name.
     *
     * @param displayedName the displayed name
     */
    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    /**
     * Check user boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean checkUser(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){return true;}
        return false;
    }

    /**
     * Check user boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean checkUser(String username){
        if(this.username.equals(username)){return true;}
        return false;
    }

    /**
     * To csv string.
     * Converts system data to String to write it in a file.
     * @return the string
     */
    public abstract String toCSV();

    /**
     * Get type string.
     *
     * @return the string
     */
    public String getType(){
        return this.getClass().getSimpleName();
    }
}