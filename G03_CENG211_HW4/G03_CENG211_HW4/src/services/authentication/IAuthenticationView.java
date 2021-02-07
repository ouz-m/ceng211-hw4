package services.authentication;

import models.user.User;

/**
 * Authentication view interface
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public interface IAuthenticationView {
    /**
     * Ask sign in or sign up.
     */
    void askSignInOrSignUp();

    /**
     * Enter valid selection.
     */
    void enterValidSelection();

    /**
     * Incorrect username or password.
     */
    void incorrectUsernameOrPassword();

    /**
     * Ask sign up credentials again.
     */
    void askSignUpCredentialsAgain();

    /**
     * Sign in successful view.
     *
     * @param user the user
     */
    void signInSuccessfulView(User user);

    /**
     * Sign up successful view.
     *
     * @param user the user
     */
    void signUpSuccessfulView(User user);

    /**
     * Ask username.
     */
    void askUsername();

    /**
     * Ask password.
     */
    void askPassword();

    /**
     * Ask displayed name.
     */
    void askDisplayedName();

    /**
     * Ask account type.
     */
    void askAccountType();

    /**
     * Ask enter selection.
     */
    void askEnterSelection();

    /**
     * Seperator.
     */
    void seperator();
}
