package services.database;

/**
 * Table service interface
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public interface ITable {

    /**
     * To csv array string [ ].
     * Converts system data to String array to write it to file.
     * @return the string [ ]
     */
    String[] toCSVArray();
}
