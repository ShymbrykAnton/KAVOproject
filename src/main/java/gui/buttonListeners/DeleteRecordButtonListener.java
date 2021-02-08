package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import gui.buttonListeners.controller.ListenerController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class DeleteRecordButtonListener implements ActionListener {
    private final JTextField idTextField;
    private final JTextField fNameTextField;
    private final JTextField lNameTextField;
    private final JTextField ageTextField;
    private final JTextField cityTextField;
    private final ListenerController listenerController;

    public DeleteRecordButtonListener(JTextField idTextField,
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
//        FileHelper fileHelper = new FileHelper();

        String filename = listenerController.getFilename();
        Executable executable = listenerController.getExecutable();

        List<Person> personList = executable.read(filename);

        long id = Long.parseLong(idTextField.getText());
//        fileHelper.idValidation(personList,id);

        executable.delete(id, personList, filename);

        listenerController.getTable().redrawTable(filename,executable);

        listenerController.setTextFieldEmpty(idTextField, fNameTextField,
                lNameTextField, ageTextField, cityTextField);
    }
}
