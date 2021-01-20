package gui.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainMenu extends Component {
    JFrame frame;
    MenuBar mb;
    JLabel myTableLabel, label, label1, label2, label3, label4, label5;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton buttonCreate, buttonUpdate, buttonDelete, buttonClearAll, buttonExit;
    public DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
            {"1", "Mike", "Kozlow", "24", "Kharkov"},
    },
            new Object[]{"Id", "First name", "Last name", "Age", "City"});

    public static JTable myTable;
    public String TITLE_confirm = "Окно подтверждения";

    public MainMenu() {

        frame = new JFrame("CRUD by KABO");
        mb = new MenuBar();
        label = new JLabel("Control Panel");
        myTableLabel = new JLabel("TABLE");
        myTable = new JTable(tableModel);
        myTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(myTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        myTableLabel.setBounds(185, 50, 100, 30);
        label.setBounds(550, 50, 100, 30);
        label1 = new JLabel("Id: ");
        label1.setBounds(400, 100, 50, 30);
        tf1 = new JTextField();
        tf1.setBounds(450, 100, 300, 30);
        label2 = new JLabel("Fname: ");
        label2.setBounds(400, 130, 50, 30);
        tf2 = new JTextField();
        tf2.setBounds(450, 130, 300, 30);
        label3 = new JLabel("Lname: ");
        label3.setBounds(400, 160, 50, 30);
        tf3 = new JTextField();
        tf3.setBounds(450, 160, 300, 30);
        label4 = new JLabel("Age: ");
        label4.setBounds(400, 190, 50, 30);
        tf4 = new JTextField();
        tf4.setBounds(450, 190, 300, 30);
        label5 = new JLabel("City: ");
        label5.setBounds(400, 220, 50, 30);
        tf5 = new JTextField();
        tf5.setBounds(450, 220, 300, 30);
//////////////////////////////Создание кнопок//////////////////////////////////////////////
        buttonCreate = new JButton("Create");//creating instance of JButton
        buttonCreate.setBounds(450, 250, 100, 40);
        buttonUpdate = new JButton("Update");//creating instance of JButton
        buttonUpdate.setBounds(550, 250, 100, 40);
        buttonDelete = new JButton("Delete");//creating instance of JButton
        buttonDelete.setBounds(650, 250, 100, 40);
        buttonClearAll = new JButton("Clear All");
        buttonClearAll.setBounds(50, 450, 100, 75);
        buttonExit = new JButton("Exit");
        buttonExit.setBounds(625, 450, 100, 75);
////////////////////////////////////////////////////////////////////////////////////////////

        frame.setMenuBar(mb);
        Menu dbsFiles = new Menu("DBS, Files");
        mb.add(dbsFiles);
        MenuItem mySql = new MenuItem("MySql");
        dbsFiles.add(mySql);
        MenuItem postgreSQL = new MenuItem("PostgreSQL");
        dbsFiles.add(postgreSQL);
        MenuItem h2 = new MenuItem("H2");
        dbsFiles.add(h2);
        MenuItem mongoDB = new MenuItem("MongoDB");
        dbsFiles.add(mongoDB);
        MenuItem redis = new MenuItem("Redis");
        dbsFiles.add(redis);
        MenuItem cassandra = new MenuItem("Cassandra");
        dbsFiles.add(cassandra);
        MenuItem graphDB = new MenuItem("GraphDB");
        dbsFiles.add(graphDB);

///////////////////Добавляет подчеркнутую линию между группами DBS и Files////////////
        dbsFiles.addSeparator();
//////////////////////////////////////////////////////////////////////////////////////
        MenuItem binary = new MenuItem("Binary");
        dbsFiles.add(binary);
        MenuItem json = new MenuItem("JSON");
        dbsFiles.add(json);
        MenuItem csv = new MenuItem("CSV");
        dbsFiles.add(csv);
        MenuItem xml = new MenuItem("XML");
        dbsFiles.add(xml);
        MenuItem yaml = new MenuItem("YAML");
        dbsFiles.add(yaml);


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
        buttonClearAll.addActionListener(e -> JOptionPane.showConfirmDialog
                (MainMenu.this,
                "Are you sure you want to delete everything?",
                TITLE_confirm, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE));
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////Закрытие приложения через "крестик"/////////////////////
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Exit();
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            }
        });
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////Закрытие приложения через кнопку "Exit"/////////////////////
        buttonExit.addActionListener(e -> Exit());
    }
/////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////Метод закрытия приложения////////////////////////////////////
    private void Exit() {
        int res = JOptionPane.showConfirmDialog(MainMenu.this,
                "Are you sure you want to close the application?",
                TITLE_confirm, JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
}






