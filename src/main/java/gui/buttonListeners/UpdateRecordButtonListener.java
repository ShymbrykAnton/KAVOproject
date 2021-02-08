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
import java.util.List;


public class UpdateRecordButtonListener implements ActionListener {
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;
    private final ListenerController listenerController;
    private final FileHelper fileHelper = new FileHelper();


    public UpdateRecordButtonListener(JTextField idTextField,
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

        List<Person> personList = executable.read(filename);


        String id = idTextField.getText();

        String fName = fNameTextField.getText();
        String lName = lNameTextField.getText();
        String age = ageTextField.getText();

        long idNum = 0;
        if (!ageTextField.getText().equals("") || !idTextField.getText().equals("")) {
            try {
                idNum = Long.parseLong(id);
                fileHelper.idValidationForUpdDel(personList, idNum);
                fileHelper.ageValidation(Integer.parseInt(age));
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(new Label(), exception.getMessage(), Constants.Messages.ERROR, JOptionPane.ERROR_MESSAGE);
                listenerController.setTextFieldEmpty(idTextField, fNameTextField,
                        lNameTextField, ageTextField, cityTextField);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(new Label(), Constants.Messages.ID_FIELD_IS_EMPTY, Constants.Messages.ERROR, JOptionPane.ERROR_MESSAGE);
            listenerController.setTextFieldEmpty(idTextField, fNameTextField,
                    lNameTextField, ageTextField, cityTextField);
            return;
        }
        String city = cityTextField.getText();

        for (Person iterPerson : personList) {
            if (iterPerson.getId() == idNum) {
                if (fName.equals("")) {
                    fName = iterPerson.getFName();
                }
                if (lName.equals("")) {
                    lName = iterPerson.getLName();
                }
                if (age.equals("")) {
                    age = String.valueOf(iterPerson.getAge());
                }
                if (city.equals("")) {
                    city = iterPerson.getCity();
                }
                break;
            }
        }
        String[] newValue = {id, fName, lName, age, city};

        executable.update(idNum, newValue, personList, filename);

        listenerController.getTable().redrawTable(filename, executable);

        listenerController.setTextFieldEmpty(idTextField, fNameTextField,
                lNameTextField, ageTextField, cityTextField);
    }
}
