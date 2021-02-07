package services.authentication;

import models.user.User;

/**
 * Authentication view class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class AuthenticationView implements IAuthenticationView{
    @Override
    public void askSignInOrSignUp() {
        System.out.println("Enter 1 to sign in, enter 2 to sign up ('exit' to exit the program): ");
    }

    @Override
    public void enterValidSelection() {
        System.out.println("Please enter a valid selection:");
    }

    @Override
    public void incorrectUsernameOrPassword() {
        System.out.println("Incorrect username or password! \nPlease login again.");
    }

    @Override
    public void askSignUpCredentialsAgain() {
        System.out.println("There is an account that matches with your username! \nPlease enter your sign up credentials again.");
    }

    @Override
    public void signInSuccessfulView(User user) {
        System.out.println("Welcome back " +
                user.getDisplayedName() +
                " you successfully logged in as " +
                user.getType());
    }

    @Override
    public void signUpSuccessfulView(User user) {
        System.out.println("Welcome " +
                user.getDisplayedName() +
                " you successfully signed up as " +
                user.getType());
    }

    @Override
    public void askUsername() {
        System.out.println("Please enter your username or 'back' to go back (There may be no username called 'back')");
        seperator();
        System.out.println("username: ");
    }

    @Override
    public void askPassword() {
        System.out.println("Please enter your password");
        System.out.println("password: ");
    }

    @Override
    public void askDisplayedName() {
        System.out.println("displayed name: ");
    }

    @Override
    public void askAccountType() {
        System.out.println("account type,");
    }

    @Override
    public void askEnterSelection() {
        System.out.println("1-Customer");
        System.out.println("2-Producer");
        System.out.println("Enter your selection as a number:");
    }
    @Override
    public void seperator(){
        System.out.println("---");
    }
}
