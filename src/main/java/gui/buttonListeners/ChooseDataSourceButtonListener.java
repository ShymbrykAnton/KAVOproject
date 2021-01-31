package gui.buttonListeners;

import blogic.filetype.executor.Executable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import static gui.view.MainMenu.table;
import static util.Constants.Messages.*;


public class ChooseDataSourceButtonListener implements ActionListener {
    private GetName getName = new GetName();
    private MenuBar menuBar;
    private final Menu yourFileName = new Menu();


    public ChooseDataSourceButtonListener(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String format;
        String filename = "";

        String dataSource = e.getActionCommand();

        format = dataSource.toLowerCase(Locale.ROOT);
        String input = JOptionPane.showInputDialog(new JLabel(), ENTER_FILENAME,
                CHOOSE_FILENAME, JOptionPane.INFORMATION_MESSAGE);
        if (input == null || input.equals("")) {
            return;
        }
        filename = getName.constructFileName(input, format);
        System.out.println(filename);
        yourFileName.setLabel(String.format(YOUR_FILE, filename));
        menuBar.add(yourFileName);
        System.out.println(yourFileName.getLabel());
        Executable executable = getName.constructExecutable(format);
        table.drawTable(filename, executable);
    }

}