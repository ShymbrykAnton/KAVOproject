package gui.view;

import blogic.model.Person;
import util.io.FileHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

import static gui.buttonListeners.ChooseDataSourceButtonListener.executable;
import static util.Constants.View.*;
import static util.Constants.Config.*;


public class Table {
    private final FileHelper fileHelper = new FileHelper();
    private final Frame frame;
    static JScrollPane scrollPane;

    public Table(Frame frame) {
        this.frame = frame;
    }

    public void drawTable() {
        if (!fileHelper.fileExists()) {
            personList = new ArrayList<>();
        } else {
            personList = executable.read(filename);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{},
                new Object[]{ID, FIRST_NAME, LAST_NAME, AGE, CITY});
        for(Person p : personList) {
            defaultTableModel.addRow(new String[]{String.valueOf(p.getId()),p.getFName(),p.getLName(),p.getCity(),String.valueOf(p.getAge())});
        }
        JTable defaultTable = new JTable(defaultTableModel);
        defaultTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(defaultTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 100, 300, 200);
        frame.add(scrollPane);
    }

    public void redrawTable() {
        frame.remove(scrollPane);
        drawTable();
        frame.add(scrollPane);
    }
}

//personList.toAray() - передает одномерный, в котором одна персона это один элемент {person1,person2,person3,person4,person5}
// personList - делал двумерный масив, элементами которого являются массивы персон (полями) {{ID, FIRST_NAME, LAST_NAME, AGE, CITY - person1}{}{}{}{}}