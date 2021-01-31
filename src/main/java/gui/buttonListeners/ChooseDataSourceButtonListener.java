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


public class ChooseDataSourceButtonListener implements ActionListener {
    private final MenuBar menuBar;
    private final Menu yourFileName = new Menu();
    private final FileTypeFactory fileTypeFactory = new FileTypeFactory();

    public ChooseDataSourceButtonListener(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = "";
        String format = e.getActionCommand().toLowerCase(Locale.ROOT);
        String input = JOptionPane.showInputDialog(new JLabel(), ENTER_FILENAME,
                CHOOSE_FILENAME, JOptionPane.INFORMATION_MESSAGE);
        if (input == null || input.equals("")) {
            return;
        }
        filename = String.format(FILE_FORMAT, input, format);
        yourFileName.setLabel(String.format(YOUR_FILE, filename));

        menuBar.add(yourFileName);

        Executable executable = fileTypeFactory.getInstance(format);
        table.redrawTable(filename, executable);
    }
}