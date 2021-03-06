package view;

import dao.GiaovuDAO;
import entity.GiaovuEntity;
import org.example.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GiaoVuLogIn extends JFrame {
    private JPanel panel1;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JLabel sinhvienButton;
    private JDialog dialog;

    public GiaoVuLogIn() {
        add(panel1);
        init();
        getRootPane().setDefaultButton(logInButton);
        setResizable(false);
        setSize(400,300);
        setLocationRelativeTo(null);
        setTitle("ĐĂNG NHẬP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);



    }

    private void init() {
        sinhvienButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sinhvienButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.studentLogIn = new StudentLogIn();
                App.giaoVuLogIn = null;
                dispose();
            }
        });
        logInButton.addActionListener(this::logInAction);
    }

    public void logInAction(ActionEvent e) {
        String username = userField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if (username.isEmpty()) {
            dialog = new ErrorDialog("Chưa điền tên đăng nhập");
            dialog.setVisible(true);
            return;
        } else if (password.isEmpty()) {
            dialog = new ErrorDialog("Chưa điền mật khẩu");
            dialog.setVisible(true);
            return;
        }
        GiaovuEntity gv = GiaovuDAO.LogIn(username, password);
        if(gv == null) {
            dialog = new ErrorDialog("Tên đăng nhập hoặc mật khẩu sai");
            dialog.setVisible(true);
            return;
        }
        App.currentGV = gv;
        App.gvDashboard = new GVDashboard();
        App.giaoVuLogIn = null;
        dispose();
    }
}
