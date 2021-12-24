package xyz.xesacat.rpg;

import xyz.xesacat.rpg.gui.MainFrame;

public class Main {

    public static void main(String[] args) {
        MainFrame gui = new MainFrame(args);
        gui.setTitle("Hello World");
        gui.setVisible(true);
        gui.run();
        System.exit(0);
    }
}
