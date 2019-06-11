package Observer;

import model.Person.User;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class LibraryLog extends JFrame implements Observer{
    //set the constants for the size of the window(frame), and set the font
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private static final Font TEXT_FONT = new Font("ITALIC", Font.BOLD, 15);

    //instance variables
    private JTextArea textArea;
    private String recordString;

    public LibraryLog() {
        textArea = new JTextArea();
        recordString = "";
        textArea.setFont(TEXT_FONT);
        add(new JScrollPane(textArea));

        //set the window(frame)
        setTitle("Library Log");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);

    }

    public void record(String msg) {
        textArea.append(msg + "\n");
        recordString = recordString + msg + "\r\n";
    }

    public String getRecordString() {
        return recordString;
    }

    @Override
    public void update(Observable o, Object arg) {
        User user = (User)arg;
        record("The user " + user.getPersonName() + " has borrowed " + user.getPersonalBookList() + "\n") ;
    }
}
