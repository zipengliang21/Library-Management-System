package ui;

import Utility.SetBackground;
import model.Library;
import model.Person.Librarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utility.GenerateSoundWithoutStop.generateMusic;
import static Utility.JOptionPane.displayWindow;

//The background picture is download from Google!
//The music is also download from Google
public class LibrarianLoginInterface extends JFrame implements LibraryInterface{
    private SetBackground backgroundImage;
    private JPanel frame1;
    private JTextArea welcome;
    private JTextArea userNameLabel;
    private JTextArea passwordLabel;
    private JTextArea userName;
    private JTextArea password;
    private Button enter;
    private Button back;
    private BackListener backListener;
    private EnterListener enterListener;
    private QuitListener quitListener;
    private Button quit;
    private Library library;
    private JPanel container;

    public LibrarianLoginInterface(Library library) {
        addButtonsAndLabels();
        associateListeners();
        this.library = library;
    }

    public void addButtonsAndLabels() {
        container = new JPanel();
        container.setLayout(new BorderLayout());

        frame1 = new JPanel();
        frame1.setLayout(null);

        welcome = new JTextArea("Welcome, Librarian: ");
        welcome.setEditable(false);
        welcome.setFont(TITLE_TEXT_FONT);
        welcome.setBackground(Color.red);
        welcome.setBounds(10,10,285,40);
        frame1.add(welcome);

        userNameLabel = new JTextArea("Enter your username: ");
        userNameLabel.setEditable(false);
        userNameLabel.setFont(TEXT_FONT);
        userNameLabel.setBackground(Color.gray);
        userNameLabel.setBounds(100,100,155,20);
        frame1.add(userNameLabel);

        userName = new JTextArea();
        userName.setFont(TEXT_FONT);
        userName.setBounds(100,130,155,20);
        frame1.add(userName);

        passwordLabel = new JTextArea("Enter your password: ");
        passwordLabel.setEditable(false);
        passwordLabel.setFont(TEXT_FONT);
        passwordLabel.setBackground(Color.gray);
        passwordLabel.setBounds(100,160,155,20);
        frame1.add(passwordLabel);

        password = new JTextArea();
        password.setFont(TEXT_FONT);
        password.setBounds(100,190,155,20);
        frame1.add(password);

        enter = new Button("Enter");
        enter.setFont(TEXT_FONT);
        enter.setBounds(100,225,50,20);
        enter.setBackground(Color.lightGray);
        frame1.add(enter);

        back = new Button("back");
        back.setFont(TEXT_FONT);
        back.setBounds(180,225,50,20);
        back.setBackground(Color.lightGray);
        frame1.add(back);

        quit = new Button("quit");
        quit.setFont(TEXT_FONT);
        quit.setBounds(600,300,50,50);
        quit.setBackground(Color.red);
        frame1.add(quit);

        Image img = new ImageIcon(getClass().getResource( "10673188_084640995089_2.jpg")).getImage();
        backgroundImage = new SetBackground(img);
        backgroundImage.setBounds(0,0,LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        backgroundImage.setOpaque(false);
        frame1.add(backgroundImage);

        container.add(frame1);
        add(container);

        setTitle("Zipeng's Library Application(Librarian)");
        setSize(LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        setLocation(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public void associateListeners() {
        backListener = new BackListener();
        back.addActionListener(backListener);
        
        enterListener = new EnterListener();
        enter.addActionListener(enterListener);

        quitListener = new QuitListener();
        quit.addActionListener(quitListener);
    }

    private class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            WelcomeInterface welcomeInterface = new WelcomeInterface(library);
        }
    }

    private class EnterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = userName.getText().trim();
            String loginPassword = password.getText().trim();
            if((name.equals(Librarian.librarianName)) && (loginPassword.equals(Librarian.password))) {
                setVisible(false);
                Librarian librarian = new Librarian(name, library, library.getDatabase());
                generateMusic("1_person_cheering.wav");
                LibrarianInterface librarianInterface = new LibrarianInterface(library, librarian);
            }
            else if ((name.equals("")) && (loginPassword.equals(""))) {
                generateMusic("beep-03.wav");
                displayWindow("Both username and password cannot empty");
            }
            else if(name.equals("")) {
                generateMusic("beep-03.wav");
                displayWindow("The username cannot be empty");
            }
            else if (loginPassword.equals("")) {
                generateMusic("beep-03.wav");
                displayWindow("The password cannot be empty");
            }
            else {
                generateMusic("beep-03.wav");
                displayWindow("invalid username or password!");
            }
        }
    }

    private class QuitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(1);
        }
    }
}
