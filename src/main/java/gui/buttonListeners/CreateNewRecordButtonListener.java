package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import gui.buttonListeners.controller.ListenerController;
import gui.view.Table;
import util.io.FileHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.DataSource.*;


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

        String format = filename.substring(filename.lastIndexOf('.') + 1);

        List<Person> personList;
        if (format.equals(MY_SQL)
                || format.equals(POSTGRE_SQL) || format.equals(CASSANDRA)
                || format.equals(GRAPH_DB) || format.equals(H2)
                || format.equals(MONGO_DB) || format.equals(REDIS)
                || fileHelper.fileExists(filename)) {
            personList = executable.read(filename);

        } else {
            personList = new ArrayList<>();

        }

        long id = Long.parseLong(idTextField.getText());


        fileHelper.idValidation(personList, id);

        String firstName = fNameTextField.getText();
        String lastName = lNameTextField.getText();
        byte age = Byte.parseByte((ageTextField.getText()));
        //надо протестить

        fileHelper.ageValidation(age);


        String city = cityTextField.getText();

        if (!fileHelper.fileExists(filename)) {
            personList = new ArrayList<>();
        } else {
            personList = executable.read(filename);
        }

        personList.add(new Person(id, firstName, lastName, age, city));

        executable.create(filename, personList);

        listenerController.getTable().redrawTable(filename, executable);

        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }
}