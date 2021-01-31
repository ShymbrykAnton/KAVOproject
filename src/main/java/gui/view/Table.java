package gui.view;

import blogic.filetype.executor.Executable;
import blogic.model.Person;
import gui.buttonListeners.GetName;
import util.io.FileHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.View.*;


public class Table {
    private final FileHelper fileHelper = new FileHelper();
    private final GetName getName;
    private final Frame frame;
    private JScrollPane scrollPane;


    public Table(Frame frame) {
        this.frame = frame;
        getName = new GetName();
    }

    public void drawTable(String fileName, Executable executable) {

        List<Person> personList;

        if (!fileHelper.fileExists(fileName)) {
            personList = new ArrayList<>();
        } else {
            personList = executable.read(fileName);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{},
                new Object[]{ID, FIRST_NAME, LAST_NAME, AGE, CITY});
        for (Person person : personList) {
            defaultTableModel.addRow(new String[]{String.valueOf(person.getId()), person.getFName(), person.getLName(), String.valueOf(person.getAge()), person.getCity()});
        }
        JTable defaultTable = new JTable(defaultTableModel);
        defaultTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(defaultTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 100, 400, 200);
        frame.add(scrollPane);
    }

    public void redrawTable(String fileName, Executable executable) {
        frame.remove(scrollPane);
        drawTable(fileName,executable);
        frame.add(scrollPane);
    }
}

//personList.toAray() - передает одномерный, в котором одна персона это один элемент {person1,person2,person3,person4,person5}
// personList - делал двумерный масив, элементами которого являются массивы персон (полями) {{ID, FIRST_NAME, LAST_NAME, AGE, CITY - person1}{}{}{}{}}