package gui.windowListenersControllers;

import gui.buttonListeners.ExitButtonListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static util.Constants.Messages.CONFIRM_WINDOW;
import static util.Constants.Messages.EXIT_CONFORMATION;

public class WindowExitProgramController extends WindowAdapter {
    JFrame jFrame;

    public WindowExitProgramController(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void windowClosing(WindowEvent e) {
        int res = JOptionPane.showConfirmDialog(
                new JLabel(),
                EXIT_CONFORMATION,
                CONFIRM_WINDOW,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (res == JOptionPane.YES_OPTION) {
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (res == JOptionPane.NO_OPTION) {
            jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
}
