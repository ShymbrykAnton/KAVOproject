package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import util.io.FileHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static gui.view.MainMenu.table;

public class CreateNewRecordButtonListener implements ActionListener {
    private final FileHelper fileHelper = new FileHelper();
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;
    private final GetName getName = new GetName();




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
        String filename = getName.getFileName();
        Executable executable = getName.getExecutable();
        List<Person> personList;
        long id = Long.parseLong(idTextField.getText());
        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String city = cityTextField.getText();

        if (!fileHelper.fileExists(filename)) {
            personList = new ArrayList<>();
        } else {
            personList = executable.read(filename);
        }

        personList.add(new Person(id, firstName, lastName, age, city));
        executable.create(filename, personList);
        table.redrawTable(filename,executable);

        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}