package gui.buttonListeners;

import util.io.FileHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Constants.View.*;

public class ExitButtonListener implements ActionListener {


    public ExitButtonListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int res = JOptionPane.showConfirmDialog(new JLabel(),
                "Are you sure you want to close the application?",
                CONFIRM_WINDOW, JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
