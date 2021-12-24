package xyz.xesacat.rpg.gui;

import xyz.xesacat.rpg.util.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static int[] size = { 800, 600 };
    private MainKeyListener keyboard;
    private JPanel panel;
    private JLabel label;

    public MainFrame(String[] args) {
        try {
            size = new ArrayConverter(new CmdLineParser(args).parseArgument("size", 2)).StringToInt();
        } catch (MalformedArgumentException e) {
            System.out.println(e.getMessage());
        }

        keyboard = new MainKeyListener();
        panel = new JPanel();
        label = new JLabel("Size: " + size[0] + ", " + size[1]);

        System.out.println("Size: " + size[0] + ", " + size[1]);

        addKeyListener(keyboard);
        add(panel);
        panel.add(label);

        label.setForeground(Color.white);
        panel.setBackground(Color.black);
        setResizable(false);
        setSize(size[0], size[1]);
        setMinimumSize(new Dimension(size[0], size[1]));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    public void run() {
        while (true) {
            keyboard.poll();

            if (keyboard.keyDownOnce(KeyEvent.VK_ESCAPE))
                break;
        }
    }
}
