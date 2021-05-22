package view;

import dao.GiaovuDAO;

import javax.swing.*;
import java.awt.*;

public class CreateGiaoVu extends JFrame {
    private JTextField fullNameField;
    private JPasswordField passwordField1;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel panel;
    private JDialog dialog;
    GVDashboard gvDashboard;

    public CreateGiaoVu(GVDashboard gvDashboard) {
        add(panel);
        init();
        this.gvDashboard = gvDashboard;
        setTitle("Tạo giáo vụ");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> {
            if (fullNameField.getText().isEmpty() ||  String.valueOf(passwordField1.getPassword()).isEmpty() ) {
                dialog = new ErrorDialog("Họ tên hoặc mật khẩu trống");
                dialog.setVisible(true);
            }
            if (GiaovuDAO.register(fullNameField.getText(), String.valueOf(passwordField1.getPassword()))) {
                dialog = new SuccessDialog("Thêm giáo vụ thành công");
                dialog.setVisible(true);
                gvDashboard.updateListTable();
                dispose();
            } else {
                dialog = new ErrorDialog("Không thể thêm");
                dialog.setVisible(true);
            }
        });
    }
}
