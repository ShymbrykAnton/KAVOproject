package gui.view;

import blogic.model.Person;
import gui.buttonListeners.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.View.*;
import static util.Constants.DataSource.*;
import static util.Constants.Messages.*;


public class MainMenu extends Component {
    private final JFrame frame;
    private final MenuBar mb;
    private final List<Person> person = new ArrayList<>();
    private final JLabel tableLabel, controlPanelLabel, idLabel, fNameLabel, lNameLabel, ageLabel, cityLabel;
    private final JTextField idTextField, fNameTextField, lNameTextField, ageTextField, cityTextField;
    private final JButton buttonCreate, buttonUpdate, buttonDelete, buttonClearAll, buttonExit;
    public static Table table;

    public MainMenu() {
        frame = new JFrame(PROGRAM_NAME);
        table = new Table(frame);
        mb = new MenuBar();
        frame.setJMenuBar(new JMenuBar());

        tableLabel = new JLabel(FILE_CONTENTS);
        tableLabel.setBounds(185, 50, 100, 30);

        controlPanelLabel = new JLabel(CONTROL_PANEL);
        controlPanelLabel.setBounds(550, 50, 100, 30);

        idLabel = new JLabel(String.format(LABEL_FORMAT, ID));
        idLabel.setBounds(400, 100, 50, 30);
        idTextField = new JTextField();
        idTextField.setBounds(450, 100, 300, 30);

        fNameLabel = new JLabel(String.format(LABEL_FORMAT, FIRST_NAME));
        fNameLabel.setBounds(400, 130, 50, 30);
        fNameTextField = new JTextField();
        fNameTextField.setBounds(450, 130, 300, 30);

        lNameLabel = new JLabel(String.format(LABEL_FORMAT, LAST_NAME));
        lNameLabel.setBounds(400, 160, 50, 30);
        lNameTextField = new JTextField();
        lNameTextField.setBounds(450, 160, 300, 30);

        ageLabel = new JLabel(String.format(LABEL_FORMAT, AGE));
        ageLabel.setBounds(400, 190, 50, 30);
        ageTextField = new JTextField();
        ageTextField.setBounds(450, 190, 300, 30);

        cityLabel = new JLabel(String.format(LABEL_FORMAT, CITY));
        cityLabel.setBounds(400, 220, 50, 30);
        cityTextField = new JTextField();
        cityTextField.setBounds(450, 220, 300, 30);
//////////////////////////////Создание кнопок//////////////////////////////////////////////
        buttonCreate = new JButton(CREATE);//creating instance of JButton
        buttonCreate.setBounds(450, 250, 100, 40);
        ActionListener createActionListener = new CreateNewRecordButtonListener(idTextField, fNameTextField, lNameTextField, ageTextField, cityTextField);
        buttonCreate.addActionListener(createActionListener);

        buttonUpdate = new JButton(UPDATE);//creating instance of JButton
        buttonUpdate.setBounds(550, 250, 100, 40);
        ActionListener updateActionListener = new UpdateRecordButtonListener(idTextField,fNameTextField,lNameTextField,ageTextField,cityTextField);
        buttonUpdate.addActionListener(updateActionListener);

        buttonDelete = new JButton(DELETE);//creating instance of JButton
        buttonDelete.setBounds(650, 250, 100, 40);
        ActionListener deleteActionListener = new DeleteRecordButtonListener(idTextField);
        buttonDelete.addActionListener(deleteActionListener);

        buttonClearAll = new JButton(CLEAR_ALL);
        buttonClearAll.setBounds(50, 450, 100, 75);
        ActionListener clearAllActionListener = new ClearAllDataButtonListener();
        buttonClearAll.addActionListener(clearAllActionListener);

        buttonExit = new JButton(EXIT);
        buttonExit.setBounds(625, 450, 100, 75);
        ActionListener exitActionListener = new ExitButtonListener();
        buttonExit.addActionListener(exitActionListener);
////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////Добавляет подчеркнутую линию между группами DBS и Files////////////
        dbsFiles.addSeparator();
//////////////////////////////////////////////////////////////////////////////////////
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

        ActionListener chooseDataSource = new ChooseDataSourceButtonListener(mb);
        dbsFiles.addActionListener(chooseDataSource);
        table.drawTable();
        frame.add(tableLabel);
        frame.add(controlPanelLabel);
        frame.add(idLabel);
        frame.add(fNameLabel);
        frame.add(lNameLabel);
        frame.add(ageLabel);
        frame.add(cityLabel);
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
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);

//////////////////////////////////Закрытие приложения через "крестик"/////////////////////
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                Exit();
////                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//
//            }
//        });
////////////////////////////////////////////////////////////////////////////////////////////

    }

////////////////////////////////Метод закрытия приложения////////////////////////////////////
//    private void Exit() {
//        int res = JOptionPane.showConfirmDialog(MainMenu.this,
//                "Are you sure you want to close the application?",
//                TITLE_confirm, JOptionPane.YES_NO_OPTION,
//                JOptionPane.WARNING_MESSAGE);
//        if (res == JOptionPane.YES_OPTION) {
//            System.exit(0);
//        }
}






