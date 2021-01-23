package gui.buttonListeners;

import blogic.model.Person;
import util.io.FileHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static util.Constants.View.*;

public class CreateNewRecordButtonListener implements ActionListener {
    private final FileHelper fileHelper = new FileHelper();
    private final JTextField idTextField;
    private final JTextField fnameTextField;
    private final JTextField lnameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;

    public CreateNewRecordButtonListener(JTextField idTextField, JTextField fnameTextField,
                                         JTextField lnameTextField, JTextField ageTextField,
                                         JTextField cityTextField) {
        this.idTextField = idTextField;
        this.fnameTextField = fnameTextField;
        this.lnameTextField = lnameTextField;
        this.ageTextField = ageTextField;
        this.cityTextField = cityTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long id = Long.parseLong(idTextField.getText());
        String firstName = fnameTextField.getText();
        String lastName = lnameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String city = cityTextField.getText();

        if (!fileHelper.fileExists()) {
            personList = new ArrayList<>();
        }

        try {
            personList = executable.read(filename);
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        personList.add(new Person(id, firstName, lastName, age, city));

        try {
            executable.create(filename, personList);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        idTextField.setText("");
        fnameTextField.setText("");
        lnameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}
