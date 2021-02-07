package shared;

/**
 * Constants class
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class Constants {

    /**
     * The enum Status.
     */
    public enum STATUS {
        NEW,
        SEEN,
        WORKING,
        FIXED,
        DELETED}

    /**
     * Is valid enum boolean.
     * Checks is the given string is valid in status enum
     * @param string the string
     * @return the boolean
     */
    public static boolean isValidEnum(String string){
        for (STATUS status : STATUS.values()) {
            if (status.name().equals(string.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The constant PRODUCT_TABLE path.
     */
    public final static String PRODUCT_TABLE = "HW4_ProductTableData.csv";
    /**
     * The constant COMPLAINT_TABLE path.
     */
    public final static String COMPLAINT_TABLE = "HW4_ComplaintTableData.csv";
    /**
     * The constant USER_TABLE path.
     */
    public final static String USER_TABLE = "HW4_UserTableData.csv";
}
