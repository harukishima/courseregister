package view;

import org.example.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentLogIn extends JFrame{
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JLabel giaoVuButton;
    private JPanel panel;

    public StudentLogIn() {
        add(panel);
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
    }
}
