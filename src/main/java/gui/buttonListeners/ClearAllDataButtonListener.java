package gui.buttonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static gui.view.MainMenu.table;
import static util.Constants.Config.*;
import static util.Constants.Messages.*;


public class ClearAllDataButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel confirmLabel = new JLabel(CLEAR_FILE);
        int option = JOptionPane.showConfirmDialog(confirmLabel,
                String.format(CLEAR_WARNING, filename));
        if (option == JOptionPane.YES_OPTION) {
            personList = new ArrayList<>();
            executable.create(filename, personList);
            table.redrawTable();
        }
    }
}