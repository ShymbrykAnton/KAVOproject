package gui.buttonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static util.Constants.Messages.*;

public class ExitButtonListener implements ActionListener{
    JFrame jFrame;

    public ExitButtonListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int res = JOptionPane.showConfirmDialog(
                new JLabel(),
                EXIT_CONFORMATION,
                CONFIRM_WINDOW,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (res == JOptionPane.YES_OPTION) {
            jFrame.dispose();
        }
        if (res == JOptionPane.NO_OPTION) {
            jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
}