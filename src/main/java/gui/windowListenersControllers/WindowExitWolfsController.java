package gui.windowListenersControllers;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.*;

import static util.Constants.Messages.CONFIRM_WINDOW;
import static util.Constants.Messages.EXIT_CONFORMATION_WOLFS;

public class WindowExitWolfsController extends WindowAdapter {
    JFrame jFrame;
    Clip clip;
    public WindowExitWolfsController(JFrame jFrame, Clip clip) {
         {
            this.clip = clip;
            this.jFrame = jFrame;
        }
    }

    public void windowClosing(WindowEvent e) {
        int res = JOptionPane.showConfirmDialog(new JLabel(), EXIT_CONFORMATION_WOLFS,
                CONFIRM_WINDOW, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (res == JOptionPane.YES_NO_OPTION) {
            clip.close();
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        if (res == JOptionPane.NO_OPTION) {
            jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
}
