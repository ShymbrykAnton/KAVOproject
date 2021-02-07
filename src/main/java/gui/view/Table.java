package gui.view;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import util.io.FileHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import static util.Constants.View.*;
import static util.Constants.DataSource.*;


public class Table {
    private final FileHelper fileHelper = new FileHelper();
    private final JFrame frame;
    private JScrollPane scrollPane;

    public Table(JFrame frame) {
        this.frame = frame;
    }

    public void drawTable(String fileName, Executable executable) {
        List<Person> personList;
        String format = fileName.substring(fileName.lastIndexOf('.') + 1);

        if (format.equals(MY_SQL) || format.equals(POSTGRE_SQL)
                || format.equals(CASSANDRA) || format.equals(GRAPH_DB)
                || format.equals(H2) || format.equals(MONGO_DB)
                || format.equals(REDIS)
                || fileHelper.fileExists(fileName)) {

            personList = executable.read(fileName);

        } else {
            personList = new ArrayList<>();
        }
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

    public void redrawTable(String fileName, Executable executable) {
        frame.remove(this.scrollPane);
        drawTable(fileName, executable);
    }

    private void createScrollPane(JTable defaultTable) {
        this.scrollPane = new JScrollPane(
                defaultTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        this.scrollPane.setBounds(50, 100, 400, 200);
    }
}