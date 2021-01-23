package gui.buttonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static util.Constants.View.*;

public class DeleteRecordButtonListener implements ActionListener {
    private final JTextField idTextField;

    public DeleteRecordButtonListener(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long id = Long.parseLong(idTextField.getText());
        executable.delete(id);
        try {
            executable.create(filename, personList);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        idTextField.setText("");
    }
}
