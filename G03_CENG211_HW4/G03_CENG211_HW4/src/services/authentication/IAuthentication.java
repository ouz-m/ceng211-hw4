package services.authentication;

import models.user.User;
import services.database.user_table.UserTable;

/**
 * Authentication interface
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */

public interface IAuthentication {
    /**
     * Authenticates user
     * @param userTable
     * @return
     */
    User authenticateUser(UserTable userTable);

    /**
     * Checks if given data is valid and returns the current user
     * @param userTable
     * @return
     */
    User signInUser(UserTable userTable);

    /**
     * Checks if given data is valid and returns the current user
     * @param userTable
     * @return
     */
    User signUpUser(UserTable userTable);
}
