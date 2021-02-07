package services.database.user_table;

import models.user.Producer;
import models.user.User;
import services.database.ITable;

import java.util.*;

/**
 * User table service interface
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public interface IUserTable extends ITable {

    /**
     * Searches on the table to find a correct match for username and password, if username matches checks for password in User class
     *
     * @param username the username
     * @param password the password
     * @return the user
     */
    User provideUser(String username, String password);

    /**
     * Searches on the table to find all producers and returns them fore further usages
     *
     * @return the producer list
     */
    LinkedList<Producer> getProducerList();

    /**
     * Creates new user and adds it into table according to its type
     *
     * @param username      the username
     * @param password      the password
     * @param accountType   the account type
     * @param displayedName the displayed name
     * @return the user
     */
    User createUser(String username, String password, String accountType, String displayedName);

    /**
     * Check if the user exists in the database
     *
     * @param username the username
     * @return the boolean
     */
    boolean checkUser(String username);

    /**
     * Gets displayed name of given user.
     *
     * @param userID the user id
     * @return the displayed name of given user
     */
    String getDisplayedNameOfGivenUser(String userID);
}
