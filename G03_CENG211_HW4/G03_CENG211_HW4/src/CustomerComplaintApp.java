import services.fileio.FileIO;

/**
 * Main
 *
 * @author 270201036 & 270201089
 * @author Oguzhan MERTTURK & Harun Eren MUTLU
 * @version HW4
 */
public class CustomerComplaintApp {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run(new FileIO());
    }
}
