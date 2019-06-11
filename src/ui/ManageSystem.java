package ui;

import Utility.Data;
import Utility.GenerateSound;
import model.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import static ui.LibraryInterface.TEXT_FONT;

//The background picture is download from Google!
//The music is also download from Google
public class ManageSystem extends JFrame{
    private Button startMusic;
    private Button stopMusic;
    private StopMusicListener stopMusicListener;
    private GenerateSound generateSound;
    private StartMusicListener startMusicListener;

    //EFFECTS: run the library application program
    public static void main(String[] args) throws MalformedURLException, IOException {
        Library library = new Library();
        new ManageSystem(library);
        new Data(library);
    }

    public ManageSystem(Library library) {
        startTheFrame(library);
    }

    private void startTheFrame(Library library) {
//        new Thread(library).start();
        WelcomeInterface buildGUI = new WelcomeInterface(library);
        generateSound = new GenerateSound("League of Legends - Rise.wav");
        musicFrame();
        associateListener();
    }

    public void musicFrame() {
        JFrame musicFrame = new JFrame();
        musicFrame.setLayout(null);
        startMusic = new Button("Start the Main music");
        startMusic.setFont(TEXT_FONT);
        startMusic.setBounds(0,0,250,50);
        musicFrame.add(startMusic);

        stopMusic = new Button("Stop/continue the Main music");
        stopMusic.setFont(TEXT_FONT);
        stopMusic.setBounds(0,50,250,50);
        musicFrame.add(stopMusic);

        musicFrame.setTitle("Main Music Frame");
        musicFrame.setSize(258,130);
        musicFrame.setLocation(100,500);
        musicFrame.setVisible(true);
        musicFrame.setResizable(false);
    }
    
    public void associateListener() {
        stopMusicListener = new StopMusicListener();
        startMusicListener = new StartMusicListener();

        stopMusic.addActionListener(stopMusicListener);
        startMusic.addActionListener(startMusicListener);
        
    }
    
    private class StopMusicListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            generateSound.stopMusic();
        }
    }

    private class StartMusicListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!generateSound.getClip().isActive())
                generateSound = new GenerateSound("League of Legends - Rise.wav");
        }
    }
}

//        BufferedReader br = null;
//        try {
//            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html";
//            URL url = new URL(theURL);
//            br = new BufferedReader(new InputStreamReader(url.openStream()));
//
//            String line;
//
//            StringBuilder sb = new StringBuilder();
//
//            while ((line = br.readLine()) != null) {
//
//                sb.append(line);
//                sb.append(System.lineSeparator());
//            }
//
//            displayWindow(sb.toString());
//        } finally {
//
//            if (br != null) {
//                br.close();
//            }
//        }