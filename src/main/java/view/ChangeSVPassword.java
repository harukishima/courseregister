package view;

import dao.dataCRUD;
import org.example.App;
import util.hashUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class ChangeSVPassword extends JFrame {
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    private final SVDashboard svDashboard;

    public ChangeSVPassword(SVDashboard svDashboard) throws HeadlessException {
        this.svDashboard = svDashboard;
        initForm();
    }

    private void initForm() {
        add(panel);
        setTitle("Đổi mật khẩu");
        setSize(400,200);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                svDashboard.setVisible(true);
            }
        });

        cancelButton.addActionListener(e -> {
            svDashboard.setVisible(true);
            dispose();
        });

        OKButton.addActionListener(e -> changePassword());
    }

    private void changePassword() {
        if (String.valueOf(oldPasswordField.getPassword()).isEmpty() || String.valueOf(newPasswordField.getPassword()).isEmpty() ||
                String.valueOf(confirmPasswordField.getPassword()).isEmpty()) {
            (new ErrorDialog("Chưa nhập đủ thông tin")).setVisible(true);
            return;
        }
        if (!Arrays.equals(newPasswordField.getPassword(), confirmPasswordField.getPassword())) {
            (new ErrorDialog("Mật khẩu mới không khớp, vui lòng nhập lại")).setVisible(true);
            return;
        }
        if (!hashUtils.checkPassword(String.valueOf(oldPasswordField.getPassword()),App.currentSV.getPass())) {
            (new ErrorDialog("Mật khẩu cũ không khớp, vui lòng nhập lại")).setVisible(true);
            return;
        }
        App.currentSV.setPass(hashUtils.hashPassword(String.valueOf(newPasswordField.getPassword())));
        if (dataCRUD.updateEntity(App.currentSV, App.currentSV.getMasv())) {
            (new SuccessDialog("Đổi mật khẩu thành công")).setVisible(true);
            svDashboard.setVisible(true);
            dispose();
            return;
        }
        (new ErrorDialog("Không thể đổi mật khẩu")).setVisible(true);
    }
}
