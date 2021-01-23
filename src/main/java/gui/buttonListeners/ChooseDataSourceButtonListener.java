package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.filetype.executor.factory.FileTypeFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Constants.View.*;


public class ChooseDataSourceButtonListener implements ActionListener {
    final Menu item;
    private final MenuBar menuBar;
    Menu yourFileName = new Menu();
    public static String format = "";
    public static Executable executable;
    private final FileTypeFactory fileTypeFactory = new FileTypeFactory();

    public ChooseDataSourceButtonListener(Menu item, MenuBar menuBar) {
        this.item = item;
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String dataSource = e.getActionCommand();
        switch (dataSource) {
            case MY_SQL:
                format = "MySql";
                break;
            case POSTGRE_SQL:
                format = "PostgreSQL";
                break;
            case H2:
                format = "H2";
                break;
            case MONGO_DB:
                format = "MongoDB";
                break;
            case REDIS:
                format = "Redis";
                break;
            case CASSANDRA:
                format = "Cassandra";
                break;
            case GRAPH_DB:
                format = "GraphDB";
                break;
            case BINARY:
                format = "bin";
                break;
            case JSON:
                format = "json";
                break;
            case CSV:
                format = "csv";
                break;
            case XML:
                format = "xml";
                break;
            case YAML:
                format = "yaml";
                break;
            default:
                throw new IllegalArgumentException("ЕРРОР");
        }
        JLabel label = new JLabel("sdgsdg");
        String input = JOptionPane.showInputDialog(label, ENTER_FILENAME,
                "Choose your filename", JOptionPane.INFORMATION_MESSAGE);
        if (input == null) {
            return;
        }
        if (input.equals("")) {
            return;
        }
        filename = String.format("%s.%s", input, format);
        yourFileName.setLabel(String.format("Your file: %s", filename));
        menuBar.add(yourFileName);
        executable = fileTypeFactory.getInstance();

    }
}
