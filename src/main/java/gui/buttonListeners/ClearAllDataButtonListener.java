package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import gui.buttonListeners.controller.ListenerController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Constants.Messages.*;


public class ClearAllDataButtonListener implements ActionListener {
    private final ListenerController listenerController;

    public ClearAllDataButtonListener(ListenerController listenerController) {
        this.listenerController = listenerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = listenerController.getFilename();
        Executable executable = listenerController.getExecutable();

        JLabel confirmLabel = new JLabel(CLEAR_FILE);
        int option = JOptionPane.showConfirmDialog(
                confirmLabel,
                String.format(CLEAR_WARNING, filename)
        );

        if (option == JOptionPane.YES_OPTION) {
            executable.clearAll(filename);
            listenerController.getTable().redrawTable();
        }
    }
}