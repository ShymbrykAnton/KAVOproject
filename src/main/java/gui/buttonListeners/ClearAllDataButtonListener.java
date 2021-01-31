package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static gui.view.MainMenu.table;
import static util.Constants.Messages.*;


public class ClearAllDataButtonListener implements ActionListener {
    private final GetName getName = new GetName();

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = getName.getFileName();
        Executable executable = getName.getExecutable();
        List<Person> personList;
        JLabel confirmLabel = new JLabel(CLEAR_FILE);
        int option = JOptionPane.showConfirmDialog(confirmLabel,
                String.format(CLEAR_WARNING, filename));
        if (option == JOptionPane.YES_OPTION) {
            personList = new ArrayList<>();
            executable.create(filename, personList);
            table.redrawTable(filename,executable);
        }
    }
}