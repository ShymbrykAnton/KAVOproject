package gui.buttonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static util.Constants.View.filename;
import static util.Constants.View.personList;


public class ClearAllDataButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel confirmLabel = new JLabel("Clearing file");
        JOptionPane jOptionPane = new JOptionPane();
        int shet = JOptionPane.showConfirmDialog(confirmLabel,
                String.format("Do you really want to delete all data in %s.", filename));
        if (shet == 0) {
            personList = new ArrayList<>();
            try {
                executable.create(filename,personList);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
