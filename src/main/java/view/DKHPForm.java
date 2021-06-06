package view;

import dao.HockiDAO;
import model.DKHPController;
import model.DKHPTableModel;
import org.example.App;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DKHPForm extends JFrame {
    private JPanel panel1;
    private JTable HPTable;
    private JButton confirmButton;
    private JButton abortButton;
    private final SVDashboard svDashboard;
    private DKHPController controller;
    private DKHPTableModel dkhpTableModel;

    public DKHPForm(SVDashboard svDashboard) {
        this.svDashboard = svDashboard;
        add(panel1);
        setTitle("Đăng kí học phần");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                svDashboard.setVisible(true);
            }
        });
        initForm();
    }

    public void initForm() {
        abortButton.addActionListener(e -> {
            svDashboard.setVisible(true);
            dispose();
        });
        controller = new DKHPController(HockiDAO.getCurrentHK(), App.currentSV, this);
        dkhpTableModel = new DKHPTableModel(controller);
        HPTable.setModel(dkhpTableModel);
        confirmButton.addActionListener(e -> controller.submit());
    }
}
