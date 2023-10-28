import javax.swing.*;

/**
 * This is main class to run the SongViewer App
 *
 * @author Asfandyar Tanwer
 * @version 1.0
 */
public class Main {

    /**
     * This is main method of class Main
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // asynchronous run method
        SwingUtilities.invokeLater(() -> {
            // create and call SongViewer to start app
            SongViewer gui = new SongViewer();
            gui.setVisible(true);
        });
    }
}

