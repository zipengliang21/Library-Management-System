package Utility;
public class JOptionPane {
    //EFFECTS: show the input window
    public static String getInputWindow(String message) {
        return javax.swing.JOptionPane.showInputDialog(
                null,
                message,
                "Input Option Window",
                javax.swing.JOptionPane.QUESTION_MESSAGE);
    }

    //EFFECTS: show the display window
    public static void displayWindow(String message) {
        javax.swing.JOptionPane.showMessageDialog(
                null,
                message);
    }

    //EFFECTS: show the confirm window
    public static boolean confirmWindow(String meg) {
        return (javax.swing.JOptionPane.showConfirmDialog(
                null,
                meg,
                "Confirm Window",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE)
                == javax.swing.JOptionPane.YES_OPTION);
    }

    //EFFECTS: show the warning window
    public static void warningWindow(String msg) {
        javax.swing.JOptionPane.showMessageDialog(
                null,
                msg,
                "Warning Window",
                javax.swing.JOptionPane.WARNING_MESSAGE);
    }
}
