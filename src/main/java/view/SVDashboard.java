package view;

import dao.HockiDAO;
import org.example.App;

import javax.swing.*;

public class SVDashboard extends JFrame {
    private JButton DKHPButton;
    private JPanel panel1;
    private JButton ListHPButton;
    private JButton LogOutButton;

    public SVDashboard() {
        add(panel1);
        setTitle("Trang chủ");
        setSize(300, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
    }

    public void init() {
        LogOutButton.addActionListener(e -> logOutAction());
        DKHPButton.addActionListener(e -> {
            if (!HockiDAO.checkTrongKiDK()) {
                (new ErrorDialog("Chưa đến thời gian đăng kí")).setVisible(true);
                return;
            }
            this.setVisible(false);
            new DKHPForm(this);
        });
    }

    private void logOutAction() {
        App.currentSV = null;
        App.svDashboard = null;
        App.studentLogIn = new StudentLogIn();
        dispose();
    }
}