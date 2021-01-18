package gui.view;

import javax.swing.*;


public class MainMenu {
    JFrame frame;
    JTable table;
    JLabel label, label1, label2, label3, label4, label5;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JTextArea textArea;
    JButton button1,button2,button3;
    public Object[][] array = new String[][]{{"1", "Mike", "Kozlow", "24", "Kharkov"}}; // тут Персона будет записываться
    public Object[] columnNames = new String[]{"Id", "First name", "Last name", "Age", "City"};

    public MainMenu() {
        frame = new JFrame("CRUD by KABO");
        label = new JLabel("Control Panel");

        label.setBounds(550, 150, 100, 30);
        label1 = new JLabel("Id: ");
        label1.setBounds(400, 200, 50, 30);
        tf1 = new JTextField();
        tf1.setBounds(450, 200, 300, 30);
        label2 = new JLabel("Fname: ");
        label2.setBounds(400, 230, 50, 30);
        tf2 = new JTextField();
        tf2.setBounds(450, 230, 300, 30);
        label3 = new JLabel("Lname: ");
        label3.setBounds(400, 260, 50, 30);
        tf3 = new JTextField();
        tf3.setBounds(450, 260, 300, 30);
        label4 = new JLabel("Age: ");
        label4.setBounds(400, 290, 50, 30);
        tf4 = new JTextField();
        tf4.setBounds(450, 290, 300, 30);
        label5 = new JLabel("City: ");
        label5.setBounds(400, 320, 50, 30);
        tf5 = new JTextField();
        tf5.setBounds(450, 320, 300, 30);

        button1 = new JButton("Create");//creating instance of JButton
        button1.setBounds(450, 350, 100, 40);
        button2 = new JButton("Update");//creating instance of JButton
        button2.setBounds(550, 350, 100, 40);
        button3 = new JButton("Delete");//creating instance of JButton
        button3.setBounds(650, 350, 100, 40);


        table = new JTable(array, columnNames);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        table.setBounds(25, 250, 300, 400);

        frame.add(table);
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
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.setLayout(null);
        frame.setVisible(true);
        table.setVisible(true);
    }


//    JFrame frame = new JFrame("fdshjgkdjfs");
//
//        JFrameCrud() {    //вид главного меню. Должно иметь 5 кнопок - Read data, Update record, Create new record, Remove record и Exit
//        frame = new JFrame("KABO???");
//        // при запуске также должна быть пустая таблица (JTable, TableModel, TableCellEditor - что-то из этого я так понял)

}

