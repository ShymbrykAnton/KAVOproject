package gui.buttonListeners;

import blogic.filetype.executor.Executable;
import blogic.filetype.executor.factory.FileTypeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import static gui.view.MainMenu.table;
import static util.Constants.Messages.*;
import static util.Constants.View.*;


public class ChooseDataSourceButtonListener implements ActionListener {
    private final MenuBar menuBar;
    private final Menu yourFileName = new Menu();
    private final FileTypeFactory fileTypeFactory = new FileTypeFactory();

    public ChooseDataSourceButtonListener(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename;
        String format = e.getActionCommand();
        if (format.equals(JSON) || format.equals(CSV) || format.equals(YAML) || format.equals(BINARY) || format.equals(XML)) {
           String input = JOptionPane.showInputDialog(new JLabel(), ENTER_FILENAME,
                    CHOOSE_FILENAME, JOptionPane.INFORMATION_MESSAGE);
            if (input == null || input.equals("")) {
                return;
            }
            format = format.toLowerCase(Locale.ROOT);
            filename = String.format(FILE_FORMAT, input, format);
            yourFileName.setLabel(String.format(YOUR_FILE, filename));
        } else {
            filename = format;
            yourFileName.setLabel(String.format("Your database: %s", filename));
        }
        menuBar.add(yourFileName);
        Executable executable = fileTypeFactory.getInstance(format);
        table.redrawTable(filename, executable);
    }
}