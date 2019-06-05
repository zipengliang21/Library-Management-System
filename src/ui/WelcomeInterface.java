package ui;
import Utility.SetBackground;
import model.Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The background picture is download from Google!
//The music is also download from Google
public class WelcomeInterface extends JFrame implements LibraryInterface{
    private JPanel frame1;
    private JLabel welcome;
    private Button librarian;
    private Button user;
    private Button quit;
    private SetBackground backgroundImage;
    private LibrarianLoginListener librarianLoginListener;
    private UserLoginListener userLoginListener;
    private QuitListener quitListener;
    private Library library;

    public WelcomeInterface(Library library) {
        addButtonsAndLabels();
        associateListeners();
        this.library = library;
    }

    @Override
    public void addButtonsAndLabels() {
        frame1 = new JPanel();
        frame1.setLayout(null);
        welcome = new JLabel("Welcome to the library system");
        welcome.setFont(TITLE_TEXT_FONT);
        welcome.setBounds(130,10,500,50);
        frame1.add(welcome);

        librarian = new Button("Login as Librarian");
        librarian.setFont(TEXT_FONT);
        librarian.setBounds(130,150,150,40);
        librarian.setBackground(Color.CYAN);
        frame1.add(librarian);

        user = new Button("Login as user");
        user.setFont(TEXT_FONT);
        user.setBounds(400,150,150,40);
        user.setBackground(Color.GREEN);
        frame1.add(user);

        quit = new Button("quit");
        quit.setFont(TEXT_FONT);
        quit.setBounds(600,300,50,50);
        quit.setBackground(Color.red);
        frame1.add(quit);

        Image img = new ImageIcon(getClass().getResource( "4abc1c94771d22ea8fb2135867534062.jpg")).getImage();
        backgroundImage = new SetBackground(img);
        backgroundImage.setBounds(0,0,LibraryInterface.WIDTH, LibraryInterface.HEIGHT);
        frame1.add(backgroundImage);

        add(frame1);
        setTitle("Zipeng's Library Application");
        setSize(LibraryInterface.WIDTH, LibraryInterface.HEIGHT);
        setLocation(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void associateListeners()
    {
        librarianLoginListener = new LibrarianLoginListener();
        userLoginListener = new UserLoginListener();
        quitListener = new QuitListener();

        librarian.addActionListener(librarianLoginListener);
        user.addActionListener(userLoginListener);
        quit.addActionListener(quitListener);

    }

    private class LibrarianLoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)  {
            LibrarianLoginInterface librarianLoginInterface = new LibrarianLoginInterface(library);
            dispose();
        }
    }

    private class UserLoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            UserInterface userLoginInterface = new UserInterface(library);
        }
    }

    private class QuitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
           System.exit(1);
        }
    }
}
