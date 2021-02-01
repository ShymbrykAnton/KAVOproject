package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import util.io.FileHelper;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static gui.view.MainMenu.table;
import static util.Constants.View.*;

public class UpdateRecordButtonListener implements ActionListener {
    private final FileHelper fileHelper = new FileHelper();
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;

    public UpdateRecordButtonListener(JTextField idTextField, JTextField fNameTextField,
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
        String filename = table.getFilename();
        Executable executable = table.getExecutable();
        List<Person> personList;
        String id = idTextField.getText();
        long idNum = Long.parseLong(id);
        String fName = fNameTextField.getText();
        String lName = lNameTextField.getText();
        String age = ageTextField.getText();
//        int ageValid = Integer.parseInt(age);
//        fileHelper.ageValidation(ageValid);
        String city = cityTextField.getText();
        String[] newValue = {id, fName, lName, age, city};
        personList = executable.read(filename);
        executable.update(idNum, newValue, personList);
        table.redrawTable(filename,executable);

        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}
