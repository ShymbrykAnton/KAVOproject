package gui.buttonListeners.controller;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import gui.view.Table;
import util.io.FileHelper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.DataSource.*;

public class ListenerController {
    private String filename = "";
    private Executable executable;
    private final Table table;

    public ListenerController(JFrame frame) {
        table = new Table(frame,this);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setExecutable(Executable executable) {
        this.executable = executable;
    }

    public String getFilename() {
        return filename;
    }

    public Table getTable() {
        return table;
    }

    public Executable getExecutable() {
        return executable;
    }

    public void setTextFieldEmpty(JTextField idTextField, JTextField fNameTextField,
                                  JTextField lNameTextField,JTextField ageTextField,
                                  JTextField cityTextField) {
        idTextField.setText("");
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        cityTextField.setText("");
    }

    public List<Person> getPersonList (FileHelper fileHelper){
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
        return personList;
    }
}
