package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import static gui.view.MainMenu.table;

public class DeleteRecordButtonListener implements ActionListener {
    private final JTextField idTextField;
    private final GetName getName = new GetName();

    public DeleteRecordButtonListener(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = getName.getFileName();
        Executable executable = getName.getExecutable();
        List<Person> personList = executable.read(filename);
        long id = Long.parseLong(idTextField.getText());
        executable.delete(id, personList);
        executable.create(filename, personList);
        table.redrawTable(filename,executable);
        idTextField.setText("");
    }
}
