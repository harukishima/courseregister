package view;

import dao.GiaovuDAO;
import dao.SinhvienDAO;
import entity.GiaovuEntity;
import entity.SinhvienEntity;
import org.example.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentLogIn extends JFrame{
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JLabel giaoVuButton;
    private JPanel panel;
    private JDialog dialog;

    public StudentLogIn() {
        add(panel);
        getRootPane().setDefaultButton(logInButton);
        init();
        setSize(400, 300);
        setTitle("ĐĂNG NHẬP");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }

    private void init() {
        giaoVuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        giaoVuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.giaoVuLogIn = new GiaoVuLogIn();
                App.studentLogIn = null;
                dispose();
            }
        });
        logInButton.addActionListener(this::logInAction);
    }

    public void logInAction(ActionEvent e) {
        String username = userField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if (username.isEmpty()) {
            dialog = new ErrorDialog("Chưa điền tên đăng nhập ");
            dialog.setVisible(true);
            return;
        } else if (password.isEmpty()) {
            dialog = new ErrorDialog("Chưa điền mật khẩu");
            dialog.setVisible(true);
            return;
        }
        SinhvienEntity sv = SinhvienDAO.LogIn(username, password);
        if(sv == null) {
            dialog = new ErrorDialog("Tên đăng nhập hoặc mật khẩu sai");
            dialog.setVisible(true);
            return;
        }
        App.currentSV = sv;
        App.svDashboard = new SVDashboard();
        App.studentLogIn = null;
        dispose();
    }
}
