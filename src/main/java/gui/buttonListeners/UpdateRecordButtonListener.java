package gui.buttonListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static gui.view.MainMenu.table;
import static util.Constants.Config.filename;
import static util.Constants.Config.personList;
import static util.Constants.View.*;

public class UpdateRecordButtonListener implements ActionListener {
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
        String id = idTextField.getText();
        long idNum = Long.parseLong(id);
        String fName = fNameTextField.getText();
        String lName = lNameTextField.getText();
        String age = ageTextField.getText();
        String city = cityTextField.getText();
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
        executable.update(idNum, updatingTypeValue, newValue);
        executable.create(filename, personList);
        table.redrawTable();

        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}
