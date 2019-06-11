package ui;

import Utility.LoadAndSave;
import Utility.SetBackground;
import exceptions.DuplicateBook;
import exceptions.EmptyName;
import exceptions.InvalidBookCategory;
import model.Library;
import model.Person.Librarian;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utility.GenerateSoundWithoutStop.generateMusic;
import static Utility.JOptionPane.displayWindow;
import static Utility.JOptionPane.getInputWindow;

//The background picture is download from Google!
//The music is also download from Google
public class LibrarianInterface extends JFrame implements LibraryInterface{
    private Librarian librarian;
    private Library library;
    private JPanel frame1Panel;
    private SetBackground backgroundImage;
    private JTextArea welcome;
    private Button quit;
    private Button add;
    private Button remove;
    private Button loan;
    private Button view;
    private QuitListener quitListener;
    private JFrame frame2;
    private JPanel frame2Panel;
    private JTextArea recordLog;
    private JScrollPane recordLogPane;
    private JButton logOut;
    private AddListener addListener;
    private RemoveListener removeListener;
    private LoanListener loanListener;
    private ViewListener viewListener;
    private DefaultTableModel model;
    private LogOutListener logOutListener;
    private LoadAndSave loadAndSave;
    private Button viewAllBooks;
    private ViewAllBooksListener viewAllBooksListener;


    public LibrarianInterface(Library library, Librarian librarian) {
        this.library = library;
        this.librarian = librarian;
        addButtonsAndLabels();
        associateListeners();
    }

    @Override
    public void addButtonsAndLabels() {
        mainFrame();
        recordFrame();
    }

    private void mainFrame() {
        frame1Panel = new JPanel();
        frame1Panel.setLayout(null);

        welcome = new JTextArea("Welcome, Zipeng: ");
        welcome.setEditable(false);
        welcome.setFont(TITLE_TEXT_FONT);
        welcome.setBackground(Color.red);
        welcome.setBounds(10,10,260,40);
        frame1Panel.add(welcome);

        add = new Button("Add a book");
        add.setFont(TEXT_FONT);
        add.setBounds(100,100,150,50);
        add.setBackground(Color.CYAN);
        frame1Panel.add(add);

        remove = new Button("remove a book");
        remove.setFont(TEXT_FONT);
        remove.setBounds(400,100,150,50);
        remove.setBackground(Color.CYAN);
        frame1Panel.add(remove);

        loan = new Button("Loan a book to a user");
        loan.setFont(TEXT_FONT);
        loan.setBounds(100,160,200,50);
        loan.setBackground(Color.CYAN);
        frame1Panel.add(loan);

        view = new Button("view a particular user's book list");
        view.setFont(TEXT_FONT);
        view.setBounds(100,220,275,50);
        view.setBackground(Color.CYAN);
        frame1Panel.add(view);

        viewAllBooks = new Button("view the book list of the library");
        viewAllBooks.setFont(TEXT_FONT);
        viewAllBooks.setBounds(100,280,275,50);
        viewAllBooks.setBackground(Color.CYAN);
        frame1Panel.add(viewAllBooks);

        logOut = new JButton("<HTML><U>LogOut</U></HTML>");
        logOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logOut.setFont(TEXT_FONT);
        logOut.setBounds(600,10,90,20);
        logOut.setForeground(Color.red);
        frame1Panel.add(logOut);

        quit = new Button("Quit");
        quit.setFont(TEXT_FONT);
        quit.setBounds(600,300,60,40);
        quit.setBackground(Color.red);
        frame1Panel.add(quit);

        Image img = new ImageIcon(getClass().getResource( "10673188_084640995089_2.jpg")).getImage();
        backgroundImage = new SetBackground(img);
        backgroundImage.setBounds(0,0,LibraryInterface.WIDTH,LibraryInterface.HEIGHT);
        frame1Panel.add(backgroundImage);

        add(frame1Panel);
        setTitle("Zipeng's Library Application(User)");
        setSize(LibraryInterface.WIDTH, LibraryInterface.HEIGHT);
        setLocation(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void recordFrame() {
        frame2Panel = new JPanel();
        frame2Panel.setLayout(null);
        recordLog = new JTextArea();
        recordLogPane = new JScrollPane(recordLog);
        recordLog.setEditable(false);
        recordLogPane.setBounds(10,10,350,350);
        recordLog.setText(library.toString());

        frame2 = new JFrame();
        frame2.setTitle("Record System");
        frame2.setSize(LibraryInterface.WIDTH, LibraryInterface.HEIGHT);
        frame2.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame2Panel.add(recordLogPane);
        frame2.add(frame2Panel);
        frame2.setVisible(true);
    }

    @Override
    public void associateListeners() {
        quitListener = new QuitListener();
        quit.addActionListener(quitListener);

        addListener = new AddListener();
        add.addActionListener(addListener);

        removeListener = new RemoveListener();
        remove.addActionListener(removeListener);

        loanListener = new LoanListener();
        loan.addActionListener(loanListener);

        viewListener = new ViewListener();
        view.addActionListener(viewListener);

        logOutListener = new LogOutListener();
        logOut.addActionListener(logOutListener);

        viewAllBooksListener = new ViewAllBooksListener();
        viewAllBooks.addActionListener(viewAllBooksListener);
    }

    private class QuitListener implements ActionListener {
//        @Override
        public void actionPerformed(ActionEvent event) {
            System.exit(1);
        }
    }

    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String bookName = null;
            String bookCategory = null;
            try {
            bookName = getInputWindow("Please enter the name of the book which you want to add: ");
            if (bookName.equals("")) {
                throw new EmptyName();
                }
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
                return;
            }
            catch(NullPointerException f) {
                return;
            }

            try {
            bookCategory = getInputWindow("Please enter the category of the book: ");
            if (bookCategory.equals("")) {
                    throw new EmptyName();
                }
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
                return;
            }
//            catch(InvalidBookCategory f) {
//                generateMusic("beep-03.wav");
//                displayWindow("There is no book category called \"" + f.getMessage() + "\"") ;
//            }

            try {
                librarian.addBook(bookName, bookCategory);
                recordLog.setText(library.toString());
                loadAndSave.save("dataFile.txt", library);
            }
            catch(InvalidBookCategory f) {
                generateMusic("beep-03.wav");
                displayWindow("There is no book category called \"" + f.getMessage() + "\"") ;
            }
            catch(DuplicateBook f) {
                generateMusic("beep-03.wav");
                displayWindow("The book \"" + f.getMessage() + "\" is already in the library");
            }
            catch(EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
            }
        }
    }

    private class RemoveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                librarian.removeBook();
                recordLog.setText(library.toString());
                loadAndSave.save("dataFile.txt", library);
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
            }
        }
    }

    private class LoanListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                librarian.loanBook();
                recordLog.setText(library.toString());
                loadAndSave.save("dataFile.txt", library);
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
            }
        }
    }

    private class ViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                librarian.viewOneUserBookList();
            }
            catch (EmptyName emptyName) {
                generateMusic("beep-03.wav");
                displayWindow("Please enter the name");
            }
            catch(NullPointerException f) {
            }
        }
    }

    private class LogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            frame2.dispose();
            LibrarianLoginInterface librarianLoginInterface = new LibrarianLoginInterface(library);
        }
    }

    private class ViewAllBooksListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            frame2.setVisible(true);
        }
    }
}
