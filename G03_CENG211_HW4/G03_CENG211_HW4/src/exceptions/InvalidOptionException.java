package exceptions;

/**
 * Invalid option exception class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class InvalidOptionException extends Exception{
    /**
     * Instantiates a new Invalid option exception.
     *
     * @param message the message
     */
    public InvalidOptionException(String message){super(String.format("%s is not valid.",message));}
}
