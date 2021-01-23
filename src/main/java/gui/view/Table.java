package gui.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static util.Constants.View.*;

public class Table  {

    public DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{
            personList.toArray()
    },
            new Object[]{ID, FIRST_NAME, LAST_NAME, AGE, CITY});

    public  JTable myTable = new JTable(tableModel);
//        myTable.setFillsViewportHeight(true);
    JScrollPane scrollPane = new JScrollPane(myTable,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


//        myTableLabel.setBounds(185, 50, 100, 30);
}
