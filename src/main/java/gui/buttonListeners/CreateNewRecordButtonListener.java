package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import gui.buttonListeners.controller.ListenerController;
import util.Constants;
import util.io.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class CreateNewRecordButtonListener implements ActionListener {
    private final FileHelper fileHelper = new FileHelper();
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;
    private final ListenerController listenerController;

    public CreateNewRecordButtonListener(JTextField idTextField,
                                         JTextField fNameTextField,
                                         JTextField lNameTextField,
                                         JTextField ageTextField,
                                         JTextField cityTextField,
                                         ListenerController listenerController) {
        this.idTextField = idTextField;
        this.fNameTextField = fNameTextField;
        this.lNameTextField = lNameTextField;
        this.ageTextField = ageTextField;
        this.cityTextField = cityTextField;
        this.listenerController = listenerController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String filename = listenerController.getFilename();
        Executable executable = listenerController.getExecutable();

        List<Person> personList = listenerController.getPersonList(fileHelper);

        long id = Long.parseLong(idTextField.getText());
        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        int age = Integer.parseInt((ageTextField.getText()));
        String city = cityTextField.getText();

        try {
            fileHelper.idValidationForCreate(personList, id);
            fileHelper.ageValidation(age);
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(new Label(), exception.getMessage(), Constants.Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!fileHelper.fileExists(filename)) {
            personList = new ArrayList<>();
        } else {
            personList = executable.read(filename);
        }

        personList.add(new Person(id, firstName, lastName, (byte) age, city));

        executable.create(filename, personList);

        listenerController.getTable().redrawTable();
        listenerController.setTextFieldEmpty(idTextField, fNameTextField, lNameTextField, ageTextField, cityTextField);
    }
}