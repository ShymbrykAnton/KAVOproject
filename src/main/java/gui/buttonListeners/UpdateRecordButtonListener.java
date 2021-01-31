package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static gui.view.MainMenu.table;
import static util.Constants.View.*;

public class UpdateRecordButtonListener implements ActionListener {
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;
    private final GetName getName = new GetName();

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
        String filename = getName.getFileName();
        Executable executable = getName.getExecutable();
        List<Person> personList;
        String id = idTextField.getText();
        long idNum = Long.parseLong(id);
        String fName = fNameTextField.getText();
        String lName = lNameTextField.getText();
        String age = ageTextField.getText();
        String city = cityTextField.getText();
        // todo перенос массива типов полей в метод апдейт
        String[] updatingTypeValue = new String[5];
        if (!id.equals("")) {
            updatingTypeValue[0] = ID;
        }
        if (!fName.equals("")) {
            updatingTypeValue[1] = FIRST_NAME;
        }
        if (!lName.equals("")) {
            updatingTypeValue[2] = LAST_NAME;
        }
        if (!age.equals("")) {
            updatingTypeValue[3] = AGE;
        }
        if (!city.equals("")) {
            updatingTypeValue[4] = CITY;
        }
        String[] newValue = {id, fName, lName, age, city};
        personList = executable.read(filename);
        executable.update(idNum, updatingTypeValue, newValue, personList);
        executable.create(filename, personList);
        table.redrawTable(filename,executable);

        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}
