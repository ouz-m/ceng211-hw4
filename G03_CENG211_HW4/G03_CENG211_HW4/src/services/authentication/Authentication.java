package services.authentication;

import models.user.User;
import services.database.user_table.UserTable;
import java.util.Scanner;

/**
 * Authentication service class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Authentication implements IAuthentication{

    /**
     * The Sc.
     */
    Scanner sc;
    AuthenticationView view;

    /**
     * Instantiates a new Authentication.
     *
     * @param scanner the scanner
     */
    public Authentication(Scanner scanner,AuthenticationView authenticationView){
        sc = scanner;
        view = authenticationView;
    }

    @Override
    public User authenticateUser(UserTable userTable){
        User user = null;
        switch (askAuthOption()){
            case "1" -> user =  signInUser(userTable);
            case "2" -> user =  signUpUser(userTable);
            case "exit" -> System.exit(1);
        }
        if (user == null) {
            // If user equals null, it would recall the method.
            user = authenticateUser(userTable);
        }
        return user;
    }

    /**
     * Helper method to as authentication option to user
     * @return String
     */
    private String askAuthOption(){

        view.askSignInOrSignUp();
        String selection = sc.nextLine();
        if (selection.equals("exit")) return selection;
        while (!(selection.equals("1") || selection.equals("2"))) {
            view.enterValidSelection();
            selection = sc.nextLine();
        }
        System.out.println();

        return selection;
    }

    @Override
    public User signInUser(UserTable userTable){
        User user;
        do{
            // to store username and password
            String[] credentials = askSignInCredentials();
            // to be able to go back
            if (credentials[0].equals("back")){
                return null;
            }
            user = userTable.provideUser(credentials[0], credentials[1]);
            // if there will be a mistake
            if (user == null) view.incorrectUsernameOrPassword();
        }while(user == null);

        view.signInSuccessfulView(user);

        return user;
    }

    @Override
    public User signUpUser(UserTable userTable) {
        User user;
        do{
            // to store username and password
            String[] credentials = askSignUpCredentials();
            // to be able to go back
            if (credentials[0].equals("back")){
                return null;
            }
            user = userTable.createUser(credentials[0], credentials[1], credentials[3], credentials[2]);
            // if there will be a mistake
            if (user == null) view.askSignUpCredentialsAgain();
        }while(user == null);

        view.signUpSuccessfulView(user);

        return user;
    }

    /**
     * Helper method to ask sign in credentials to user
     * @return String[]
     */
    private String[] askSignInCredentials(){
        String[] credentials = new String[2];
        view.askUsername();
        String username = sc.nextLine();
        // to be able to go back
        if(username.equalsIgnoreCase("back")){
            return new String[]{"back",""};
        }
        // username
        credentials[0] = username;
        // password
        view.askPassword();
        credentials[1] = sc.nextLine();
        view.seperator();
        return credentials;
    }

    /**
     * Helper method to ask sign up credentials to user
     * @return String[]
     */
    private String[] askSignUpCredentials(){
        String[] credentials = new String[4];
        view.askUsername();
        String username = sc.nextLine();
        // to be able to go back
        if(username.equalsIgnoreCase("back")){
            return new String[]{"back",""};
        }
        credentials[0] = username;
        // password
        view.askPassword();
        credentials[1] = sc.nextLine();
        //displayed name
        view.askDisplayedName();
        credentials[2] = sc.nextLine();
        // account type
        view.askAccountType();
        credentials[3] = askAccountType();
        view.seperator();
        return credentials;
    }

    /**
     * Helper method to get account type from user
     * @return String
     */
    private String askAccountType(){
        view.askEnterSelection();
        String type = sc.nextLine();
        while (!(type.equals("1") || type.equals("2"))) {
            view.enterValidSelection();
            type = sc.nextLine();
        }
        return type;
    }


}
