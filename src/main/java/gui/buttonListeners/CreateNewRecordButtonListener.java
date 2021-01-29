package gui.buttonListeners;

import blogic.model.Person;
import util.Constants;
import util.io.FileHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static gui.view.MainMenu.table;
import static util.Constants.Config.*;

public class CreateNewRecordButtonListener implements ActionListener {
    private final FileHelper fileHelper = new FileHelper();
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;

    public CreateNewRecordButtonListener(JTextField idTextField, JTextField fNameTextField,
                                         JTextField lNameTextField, JTextField ageTextField,
                                         JTextField cityTextField) {
        this.idTextField = idTextField;
        this.fNameTextField = fNameTextField;
        this.lNameTextField = lNameTextField;
        this.ageTextField = ageTextField;
        this.cityTextField = cityTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long id = Long.parseLong(idTextField.getText());
        fileHelper.idValidation(personList, id);
        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        byte age = Byte.parseByte((ageTextField.getText()));
        fileHelper.ageValidation(age);
        String city = cityTextField.getText();

        if (!fileHelper.fileExists()) {
            personList = new ArrayList<>();
        } else {
            personList = executable.read(filename);
        }

        personList.add(new Person(id, firstName, lastName, age, city));
        executable.create(filename, personList);
        table.redrawTable();

        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}