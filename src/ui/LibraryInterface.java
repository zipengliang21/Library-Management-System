package ui;

import java.awt.*;

public interface LibraryInterface {
    Font TITLE_TEXT_FONT = new Font("ITALIC", Font.BOLD, 30);
    Font TEXT_FONT = new Font("ITALIC", Font.BOLD, 15);

    int WIDTH = 700;
    int HEIGHT = 400;

    void addButtonsAndLabels();

    void associateListeners();


}
