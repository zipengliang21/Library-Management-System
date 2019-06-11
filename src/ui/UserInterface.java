package ui;

import Utility.SetBackground;
import exceptions.EmptyName;
import model.Library;
import model.Person.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utility.GenerateSoundWithoutStop.generateMusic;
import static Utility.JOptionPane.displayWindow;

//The background picture is download from Google!
//The music is also download from Google
public class UserInterface extends JFrame implements LibraryInterface{

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
    private JPanel frame2;
    private Button borrow;
    private Button returnOneBook;
    private Button viewOwnBooklist;
    private BorrowListener borrowListener;
    private ReturnListener returnListener;
    private ViewOwnBooklistListener viewOwnBooklistListener;
    private User user;
    private JPanel container;
    private Button logOut;
    private LogOutListener logOutListener;
    private Button goToRegister;
    private RegisterListener registerListener;
    private JPanel frame3;
    private JTextArea registerUserNameLabel;
    private JTextArea registerUserName;
    private JTextArea registerPasswordLabel;
    private JTextArea registerPassword;
    private Button register;
    private BackListener2 backListener2;
    private Button back2;
    private GoToRegisterListener goToregisterListener;
    private Button viewBooksInLibrary;
    private ViewAllBooksListener viewAllBooksListener;


    public UserInterface(Library library) {
        beforeLoginPanel();
        this.library = library;
    }

    public void beforeLoginPanel() {
        addButtonsAndLabels();
        associateListeners();
    }

    @Override
    public void addButtonsAndLabels() {
        //make a panel for before login
        frame1 = new JPanel();
        frame1.setLayout(null);

        welcome = new JTextArea("Welcome, user: ");
        welcome.setEditable(false);
        welcome.setFont(TITLE_TEXT_FONT);
        welcome.setBackground(Color.red);
        welcome.setBounds(10,10,225,40);
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

        goToRegister = new Button("New to here?  sign in here!");
        goToRegister.setFont(TEXT_FONT);
        goToRegister.setBounds(100,260,200,20);
        goToRegister.setBackground(Color.pink);
        frame1.add(goToRegister);

        Image img = new ImageIcon(getClass().getResource( "10673188_084640995089_2.jpg")).getImage();
        backgroundImage = new SetBackground(img);
        backgroundImage.setBounds(0,0,LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        frame1.add(backgroundImage);

        container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(frame1);
        add(container);

        setTitle("Zipeng's Library Application(User)");
        setSize(LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        setLocation(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        }

    private void afterLoginPanel() {
        //create a new panel for after login
        frame2 = new JPanel();
        frame2.setLayout(null);

        //welcome label
        welcome = new JTextArea("Welcome, " + user.getPersonName());
        welcome.setEditable(false);
        welcome.setFont(TITLE_TEXT_FONT);
        welcome.setBackground(Color.red);
        welcome.setBounds(10,10,275,40);
        frame2.add(welcome);

        //borrow button
        borrow = new Button("Borrow a book");
        borrow.setFont(TEXT_FONT);
        borrow.setBounds(100,100,150,50);
        borrow.setBackground(Color.CYAN);
        frame2.add(borrow);

        //return button
        returnOneBook = new Button("Return a book");
        returnOneBook.setFont(TEXT_FONT);
        returnOneBook.setBounds(400,100,150,50);
        returnOneBook.setBackground(Color.CYAN);
        frame2.add(returnOneBook);

        //view button
        viewOwnBooklist = new Button("view your personal borrowed book list");
        viewOwnBooklist.setFont(TEXT_FONT);
        viewOwnBooklist.setBounds(100,160,300,50);
        viewOwnBooklist.setBackground(Color.CYAN);
        frame2.add(viewOwnBooklist);

        viewBooksInLibrary = new Button("view all the books in the library");
        viewBooksInLibrary.setFont(TEXT_FONT);
        viewBooksInLibrary.setBounds(100,220,250,50);
        viewBooksInLibrary.setBackground(Color.CYAN);
        frame2.add(viewBooksInLibrary);

        logOut = new Button("log Out");
        logOut.setFont(TEXT_FONT);
        logOut.setBounds(600,20,70,20);
        logOut.setBackground(Color.lightGray);
        frame2.add(logOut);

        //add quit button directly since the quit button is same
        frame2.add(quit);


        Image img = new ImageIcon(getClass().getResource( "10673188_084640995089_2.jpg")).getImage();
        backgroundImage = new SetBackground(img);
        backgroundImage.setBounds(0,0,LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        frame2.add(backgroundImage);

        associateListenersAfter();

        container.remove(frame1);
        container.add(frame2);
        container.updateUI();
    }

    private void registerPanel() {
        frame3 = new JPanel();
        frame3.setLayout(null);

        welcome = new JTextArea("Welcome to Register System:");
        welcome.setEditable(false);
        welcome.setFont(TITLE_TEXT_FONT);
        welcome.setBackground(Color.red);
        welcome.setBounds(10,10,430,40);
        frame3.add(welcome);

        registerUserNameLabel = new JTextArea("Enter the username you want to use: ");
        registerUserNameLabel.setEditable(false);
        registerUserNameLabel.setFont(TEXT_FONT);
        registerUserNameLabel.setBackground(Color.gray);
        registerUserNameLabel.setBounds(100,100,260,20);
        frame3.add(registerUserNameLabel);

        registerUserName = new JTextArea();
        registerUserName.setFont(TEXT_FONT);
        registerUserName.setBounds(100,130,155,20);
        frame3.add(registerUserName);

        registerPasswordLabel = new JTextArea("Enter the password you want to use: ");
        registerPasswordLabel.setEditable(false);
        registerPasswordLabel.setFont(TEXT_FONT);
        registerPasswordLabel.setBackground(Color.gray);
        registerPasswordLabel.setBounds(100,160,258,20);
        frame3.add(registerPasswordLabel);

        registerPassword = new JTextArea();
        registerPassword.setFont(TEXT_FONT);
        registerPassword.setBounds(100,190,155,20);
        frame3.add(registerPassword);

        register = new Button("Register");
        register.setFont(TEXT_FONT);
        register.setBounds(100,230,70,20);
        register.setBackground(Color.lightGray);
        frame3.add(register);

        back2 = new Button("back");
        back2.setFont(TEXT_FONT);
        back2.setBounds(180,230,50,20);
        back2.setBackground(Color.lightGray);
        frame3.add(back2);

        Image img = new ImageIcon(getClass().getResource( "10673188_084640995089_2.jpg")).getImage();
        backgroundImage = new SetBackground(img);
        backgroundImage.setBounds(0,0,LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        frame3.add(backgroundImage);

        associateRegisterPanelListeners();

        container.remove(frame1);
        container.add(frame3);
        container.updateUI();
    }

    @Override
    public void associateListeners() {
        backListener = new BackListener();
        back.addActionListener(backListener);

        quitListener = new QuitListener();
        quit.addActionListener(quitListener);

        enterListener = new EnterListener();
        enter.addActionListener(enterListener);

        goToregisterListener = new GoToRegisterListener();
        goToRegister.addActionListener(goToregisterListener);
    }

    private void associateListenersAfter() {
        borrowListener = new BorrowListener();
        borrow.addActionListener(borrowListener);

        returnListener = new ReturnListener();
        returnOneBook.addActionListener(returnListener);

        viewOwnBooklistListener = new ViewOwnBooklistListener();
        viewOwnBooklist.addActionListener(viewOwnBooklistListener);


        viewAllBooksListener = new ViewAllBooksListener();
        viewBooksInLibrary.addActionListener(viewAllBooksListener);

        logOutListener = new LogOutListener();
        logOut.addActionListener(logOutListener);

    }

    private void associateRegisterPanelListeners() {
        registerListener = new RegisterListener();
        register.addActionListener(registerListener);

        backListener2 = new BackListener2();
        back2.addActionListener(backListener2);
    }


    private class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            WelcomeInterface welcomeInterface = new WelcomeInterface(library);
        }
    }

    private class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userNameString = userName.getText();
            String passwordString = password.getText();
            user = null;

            for (User listUser: library.getDatabase().getUserBookList().keySet()) {
                if (listUser.getPersonName().equals(userNameString) && listUser.getPassword().equals(passwordString)) {
                    user = listUser;
                }
            }
            if (!(user == null)) {
                generateMusic("1_person_cheering.wav");
                afterLoginPanel();
            }
            else if(userNameString.equals("")) {
                generateMusic("beep-03.wav");
                displayWindow("The username cannot be empty");
            }
            else if (passwordString.equals("")) {
                generateMusic("beep-03.wav");
                displayWindow("The password cannot be empty");
            }
            else {
                generateMusic("beep-03.wav");
                displayWindow("invalid username or password!!!");
            }
        }
    }

    private class QuitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(1);
        }
    }

    private class BorrowListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                user.borrowBook();
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
            }
        }
    }

    private class ReturnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                user.returnBook();
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
            }
        }
    }

    private class ViewOwnBooklistListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             user.viewPersonalBookList();
        }
    }

    private class LogOutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            container.remove(frame2);
            container.add(frame1);
            userName.setText("");
            password.setText("");
            container.updateUI();
        }
    }

    private class GoToRegisterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            registerPanel();
        }
    }

    private class BackListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userName.setText("");
            password.setText("");
            container.remove(frame3);
            container.add(frame1);
            container.updateUI();
        }
    }

    private class RegisterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String registerUserNameString = registerUserName.getText();
            String registerPasswordString = registerPassword.getText();

            if(registerUserNameString.equals("")) {
                generateMusic("beep-03.wav");
                displayWindow("The registered username cannot be empty");
                return;
            }
            else if (registerPasswordString.equals("")) {
                generateMusic("beep-03.wav");
                displayWindow("Your password cannot be empty");
                return;
            }
            for (User listUser: library.getDatabase().getUserBookList().keySet()) {
                if (listUser.getPersonName().equals(registerUserNameString)) {
                    generateMusic("beep-03.wav");
                    displayWindow("This username has already been used");
                    return;
                }
            }
            generateMusic("1_person_cheering.wav");
            displayWindow("You register successfully");
            library.register(registerUserNameString, registerPasswordString);
            container.remove(frame3);
            container.add(frame1);
            container.updateUI();
        }
    }

    private class ViewAllBooksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            user.viewBookList();
        }
    }
}
