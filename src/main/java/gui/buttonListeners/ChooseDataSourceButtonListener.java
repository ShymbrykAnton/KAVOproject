package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.filetype.executor.factory.FileTypeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.view.MainMenu.table;
import static util.Constants.View.*;
import static util.Constants.DataSource.*;
import static util.Constants.Config.*;
import static util.Constants.Messages.*;


public class ChooseDataSourceButtonListener implements ActionListener {
    private final MenuBar menuBar;
    private final Menu yourFileName = new Menu();
    public static String format = "";
    public static Executable executable;
    private final FileTypeFactory fileTypeFactory = new FileTypeFactory();

    public ChooseDataSourceButtonListener(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dataSource = e.getActionCommand();
        switch (dataSource) {
            case MY_SQL:
                format = MY_SQL;
                break;
            case POSTGRE_SQL:
                format = POSTGRE_SQL;
                break;
            case H2:
                format = H2;
                break;
            case MONGO_DB:
                format = MONGO_DB;
                break;
            case REDIS:
                format = REDIS;
                break;
            case CASSANDRA:
                format = CASSANDRA;
                break;
            case GRAPH_DB:
                format = GRAPH_DB;
                break;
            case BINARY:
                format = BINARY_TYPE;
                break;
            case JSON:
                format = JSON_TYPE;
                break;
            case CSV:
                format = CSV_TYPE;
                break;
            case XML:
                format = XML_TYPE;
                break;
            case YAML:
                format = YAML_TYPE;
                break;
        }
        String input = JOptionPane.showInputDialog(new JLabel(), ENTER_FILENAME,
                CHOOSE_FILENAME, JOptionPane.INFORMATION_MESSAGE);
        if (input == null || input.equals("")) {
            return;
        }
        //TODO вынести вот это(во всех листенерах) в отдельный метод
        filename = String.format(FILE_FORMAT, input, format);
        yourFileName.setLabel(String.format(YOUR_FILE, filename));
        menuBar.add(yourFileName);
        executable = fileTypeFactory.getInstance();
        table.redrawTable();
    }
}