package gui.view;

import blogic.model.Person;
import gui.buttonListeners.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.View.*;


public class MainMenu extends Component {
    JFrame frame;
    MenuBar mb;
    List<Person> person = new ArrayList<>();
    JLabel myTableLabel, label, label1, label2, label3, label4, label5;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton buttonCreate, buttonUpdate, buttonDelete, buttonClearAll, buttonExit;
    public DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[][]{
            person.toArray()
    },
            new Object[]{ID, FIRST_NAME, LAST_NAME, AGE, CITY});

    public JTable defaultTable;
    public String TITLE_confirm = CONFIRM_WINDOW;

    public MainMenu() {
        frame = new JFrame(PROGRAM_NAME);
        mb = new MenuBar();
        frame.setJMenuBar(new JMenuBar());
        label = new JLabel(CONTROL_PANEL);
        myTableLabel = new JLabel(FILE_CONTENTS);
        defaultTable = new JTable(defaultTableModel);
        defaultTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(defaultTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        myTableLabel.setBounds(185, 50, 100, 30);
        label.setBounds(550, 50, 100, 30);
        label1 = new JLabel(String.format("%s: ", ID));
        label1.setBounds(400, 100, 50, 30);
        tf1 = new JTextField();
        tf1.setBounds(450, 100, 300, 30);
        label2 = new JLabel(String.format("%s: ", FIRST_NAME));
        label2.setBounds(400, 130, 50, 30);
        tf2 = new JTextField();
        tf2.setBounds(450, 130, 300, 30);
        label3 = new JLabel(String.format("%s: ", LAST_NAME));
        label3.setBounds(400, 160, 50, 30);
        tf3 = new JTextField();
        tf3.setBounds(450, 160, 300, 30);
        label4 = new JLabel(String.format("%s: ", AGE));
        label4.setBounds(400, 190, 50, 30);
        tf4 = new JTextField();
        tf4.setBounds(450, 190, 300, 30);
        label5 = new JLabel(String.format("%s: ", CITY));
        label5.setBounds(400, 220, 50, 30);
        tf5 = new JTextField();
        tf5.setBounds(450, 220, 300, 30);
//////////////////////////////Создание кнопок//////////////////////////////////////////////
        buttonCreate = new JButton(CREATE);//creating instance of JButton
        buttonCreate.setBounds(450, 250, 100, 40);
        ActionListener createActionListener = new CreateNewRecordButtonListener(tf1, tf2, tf3, tf4, tf5);
        buttonCreate.addActionListener(createActionListener);
        buttonUpdate = new JButton(UPDATE);//creating instance of JButton
        buttonUpdate.setBounds(550, 250, 100, 40);
        ActionListener updateActionListener = new UpdateRecordButtonListener();
        buttonUpdate.addActionListener(updateActionListener);
        buttonDelete = new JButton(DELETE);//creating instance of JButton
        buttonDelete.setBounds(650, 250, 100, 40);
        ActionListener deleteActionListener = new DeleteRecordButtonListener(tf1);
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

        ActionListener chooseDataSource = new ChooseDataSourceButtonListener(dbsFiles, mb);
        dbsFiles.addActionListener(chooseDataSource);


        //Binary, JSON, CSV, XML, YAML
        //MySql, PostgreSQL, H2, MongoDB, Redis, Cassandra, GraphDB


        frame.add(scrollPane);
        frame.pack();
        scrollPane.setBounds(50, 100, 300, 200);
        frame.add(myTableLabel);
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(tf1);
        frame.add(tf2);
        frame.add(tf3);
        frame.add(tf4);
        frame.add(tf5);
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

/////////////////////////////////Очистка данных//////////////////////////////////////////
//        buttonClearAll.addActionListener(e -> JOptionPane.showConfirmDialog
//                (MainMenu.this,
//                        "Are you sure you want to delete everything?",
//                        TITLE_confirm, JOptionPane.OK_CANCEL_OPTION,
//                        JOptionPane.WARNING_MESSAGE));
//////////////////////////////////////////////////////////////////////////////////////////
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
////////////////////////////////Закрытие приложения через кнопку "Exit"/////////////////////
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
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
    /////////////////////////////////////////////////////////////////////////////////////////////






