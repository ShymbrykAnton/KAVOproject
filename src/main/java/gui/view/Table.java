package gui.view;

import blogic.model.Person;
import gui.buttonListeners.controller.ListenerController;
import util.PersonComparator;
import util.io.FileHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import static util.Constants.View.*;


public class Table {
    private final FileHelper fileHelper = new FileHelper();
    private final PersonComparator pc = new PersonComparator();
    private final JFrame frame;
    private JScrollPane scrollPane;
    private final ListenerController listenerController;

    public Table(JFrame frame, ListenerController listenerController) {
        this.frame = frame;
        this.listenerController = listenerController;
    }

    public void drawTable() {

        List<Person> personList = listenerController.getPersonList(fileHelper);
        personList.sort(pc);

        DefaultTableModel defaultTableModel = new DefaultTableModel(
                new Object[][]{},
                new Object[]{ID, FIRST_NAME, LAST_NAME, AGE, CITY}
        );
        for (Person person : personList) {
            defaultTableModel.addRow(
                    new String[]{
                            String.valueOf(person.getId()),
                            person.getFName(),
                            person.getLName(),
                            String.valueOf(person.getAge()),
                            person.getCity()
                    }
            );
        }
        JTable defaultTable = new JTable(defaultTableModel);
        defaultTable.setFillsViewportHeight(true);
        createScrollPane(defaultTable);

        frame.add(scrollPane);
    }

    public void redrawTable() {
        frame.remove(this.scrollPane);
        drawTable();
    }

    private void createScrollPane(JTable defaultTable) {
        this.scrollPane = new JScrollPane(
                defaultTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        this.scrollPane.setBounds(50, 90, 400, 200);
    }
}