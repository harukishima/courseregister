package view;

import dao.GiaovuDAO;
import dao.dataCRUD;
import entity.GiaovuEntity;
import model.GVTableModel;
import org.example.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class GVDashboard extends JFrame {
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton searchGVButton;
    private JButton addGVButton;
    private JButton deleteGVButton;
    private JTable GVTable;
    private JButton logOutButton;
    private JPanel panel;
    private JButton editButton;
    private GVTableModel gvTableModel;

    public GVDashboard() {
        add(panel);
        init();
        setSize(800,600);
        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        logOutButton.addActionListener(e -> {
            App.currentGV = null;
            App.gvDashboard = null;
            App.giaoVuLogIn = new GiaoVuLogIn();
            dispose();
        });
        List<GiaovuEntity> list = dataCRUD.getList(GiaovuEntity.class);
        gvTableModel = new GVTableModel(list);
        GVTable.setModel(gvTableModel);
        addGVButton.addActionListener(e -> openCreateGVForm());
        searchGVButton.addActionListener(this::searchGV);
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchGV(null);
                }
                super.keyPressed(e);
            }
        });

    }

    public void searchGV(ActionEvent e) {
        updateTable(GiaovuDAO.findGV(textField1.getText()));
    }

    public void updateListTable() {
        List<GiaovuEntity> list = dataCRUD.getList(GiaovuEntity.class);
        updateTable(list);
    }

    public void updateTable(List<GiaovuEntity> list) {
        gvTableModel.setList(list);
    }

    public void openCreateGVForm() {
        new CreateGiaoVu(this);
    }

}
