package gui.view;

import gui.buttonListeners.*;
import gui.buttonListeners.controller.ListenerController;
import gui.windowListenersControllers.WindowExitProgramController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import static util.Constants.View.*;
import static util.Constants.DataSource.*;
import static util.Constants.Messages.*;


public class MainMenu extends Component {

    public MainMenu() {
        JFrame frame = new JFrame(PROGRAM_NAME);

        ListenerController listenerController = new ListenerController(frame);

        listenerController.getTable().drawTable();

        MenuBar mb = new MenuBar();

        JLabel tableLabel = new JLabel(FILE_CONTENTS);
        tableLabel.setBounds(200, 50, 100, 30);

        JLabel controlPanelLabel = new JLabel(CONTROL_PANEL);
        controlPanelLabel.setBounds(650, 50, 100, 30);

        JLabel idLabel = new JLabel(String.format(LABEL_FORMAT, ID));
        idLabel.setBounds(500, 100, 50, 30);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(550, 100, 300, 30);

        JLabel fNameLabel = new JLabel(String.format(LABEL_FORMAT, FIRST_NAME));
        fNameLabel.setBounds(500, 130, 50, 30);

        JTextField fNameTextField = new JTextField();
        fNameTextField.setBounds(550, 130, 300, 30);

        JLabel lNameLabel = new JLabel(String.format(LABEL_FORMAT, LAST_NAME));
        lNameLabel.setBounds(500, 160, 50, 30);

        JTextField lNameTextField = new JTextField();
        lNameTextField.setBounds(550, 160, 300, 30);

        JLabel ageLabel = new JLabel(String.format(LABEL_FORMAT, AGE));
        ageLabel.setBounds(500, 190, 50, 30);

        JTextField ageTextField = new JTextField();
        ageTextField.setBounds(550, 190, 300, 30);

        JLabel cityLabel = new JLabel(String.format(LABEL_FORMAT, CITY));
        cityLabel.setBounds(500, 220, 50, 30);

        JTextField cityTextField = new JTextField();
        cityTextField.setBounds(550, 220, 300, 30);
//======================================================Создание кнопок=================================================
        JButton buttonCreate = new JButton(CREATE);
        buttonCreate.setBounds(550, 250, 100, 40);

        ActionListener createActionListener = new CreateNewRecordButtonListener(
                idTextField,
                fNameTextField,
                lNameTextField,
                ageTextField,
                cityTextField,
                listenerController
        );

        buttonCreate.addActionListener(createActionListener);

        JButton buttonUpdate = new JButton(UPDATE);
        buttonUpdate.setBounds(650, 250, 100, 40);

        ActionListener updateActionListener = new UpdateRecordButtonListener(
                idTextField,
                fNameTextField,
                lNameTextField,
                ageTextField,
                cityTextField,
                listenerController
        );
        buttonUpdate.addActionListener(updateActionListener);

        JButton buttonDelete = new JButton(DELETE);
        buttonDelete.setBounds(750, 250, 100, 40);

        ActionListener deleteActionListener = new DeleteRecordButtonListener(idTextField,
                fNameTextField,
                lNameTextField,
                ageTextField,
                cityTextField,
                listenerController);
        buttonDelete.addActionListener(deleteActionListener);

        JButton buttonClearAll = new JButton(CLEAR_ALL);
        buttonClearAll.setBounds(50, 300, 100, 50);

        ActionListener clearAllActionListener = new ClearAllDataButtonListener(listenerController);
        buttonClearAll.addActionListener(clearAllActionListener);

        JButton buttonExit = new JButton(EXIT);
        buttonExit.setBounds(750, 300, 100, 50);

        ActionListener exitActionListener = new ExitButtonListener(frame);
        buttonExit.addActionListener(exitActionListener);

        JButton buttonHomie = new JButton();
        buttonHomie.setBounds(850, 10, 5, 5);
        ActionListener buttonActionListener = new ButtonPushActionListener();
        buttonHomie.addActionListener(buttonActionListener);
//======================================================================================================================
        frame.setMenuBar(mb);
        Menu dbsFiles = new Menu(MENU_HEADLINES);
        mb.add(dbsFiles);
        MenuItem mySql = new MenuItem(MY_SQL);
        dbsFiles.add(mySql);
        MenuItem postgreSQL = new MenuItem(POSTGRE_SQL);
        dbsFiles.add(postgreSQL);
        MenuItem h2 = new MenuItem(H2);
        dbsFiles.add(h2);
        MenuItem mongoDB = new MenuItem(MONGO_DB);
        dbsFiles.add(mongoDB);
        MenuItem redis = new MenuItem(REDIS);
        dbsFiles.add(redis);
        MenuItem cassandra = new MenuItem(CASSANDRA);
        dbsFiles.add(cassandra);
        MenuItem graphDB = new MenuItem(GRAPH_DB);
        dbsFiles.add(graphDB);
//========================================Добавляет подчеркнутую линию между группами DBS и Files=======================
        dbsFiles.addSeparator();
//======================================================================================================================
        MenuItem binary = new MenuItem(BINARY);
        dbsFiles.add(binary);

        MenuItem json = new MenuItem(JSON);
        dbsFiles.add(json);

        MenuItem csv = new MenuItem(CSV);
        dbsFiles.add(csv);

        MenuItem xml = new MenuItem(XML);
        dbsFiles.add(xml);

        MenuItem yaml = new MenuItem(YAML);
        dbsFiles.add(yaml);

        ActionListener chooseDataSource = new ChooseDataSourceButtonListener(mb, listenerController);
        dbsFiles.addActionListener(chooseDataSource);

        frame.add(tableLabel);
        frame.add(idLabel);
        frame.add(fNameLabel);
        frame.add(lNameLabel);
        frame.add(ageLabel);
        frame.add(cityLabel);
        frame.add(controlPanelLabel);

        frame.add(idTextField);
        frame.add(fNameTextField);
        frame.add(lNameTextField);
        frame.add(ageTextField);
        frame.add(cityTextField);

        frame.add(buttonCreate);
        frame.add(buttonUpdate);
        frame.add(buttonDelete);
        frame.add(buttonClearAll);
        frame.add(buttonExit);
        frame.add(buttonHomie);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        WindowListener exitWindowListener = new WindowExitProgramController(frame);
        frame.addWindowListener(exitWindowListener);
        frame.setSize(900, 440);
        frame.setLocationRelativeTo(null);
    }
}