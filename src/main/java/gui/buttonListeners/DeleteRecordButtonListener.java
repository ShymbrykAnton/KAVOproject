package gui.buttonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static gui.view.MainMenu.table;
import static util.Constants.Config.*;

public class DeleteRecordButtonListener implements ActionListener {
    private final JTextField idTextField;

    public DeleteRecordButtonListener(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long id = Long.parseLong(idTextField.getText());
        executable.delete(id);
        executable.create(filename, personList);
        table.redrawTable();
        idTextField.setText("");
    }
}
