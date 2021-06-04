package view;

import dao.dataCRUD;
import entity.GiaovienEntity;

import javax.swing.*;
import java.awt.*;

public class CreateGiaoVien extends JFrame {
    private JPanel panel;
    private JTextField nameField;
    private JButton OKButton;
    private JButton cancelButton;
    private final GVDashboard gvDashboard;
    private JDialog dialog;

    public CreateGiaoVien(GVDashboard gvDashboard) throws HeadlessException {
        this.gvDashboard = gvDashboard;
        init();
    }

    public void init() {
        add(panel);
        setTitle("Thêm giáo viên");
        setSize(300, 150);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);

        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> submitNewGVien());
    }

    public void submitNewGVien() {
        if (nameField.getText().isEmpty()) {
            dialog = new ErrorDialog("Chưa nhập họ tên");
            dialog.setVisible(true);
            return;
        }
        GiaovienEntity entity = new GiaovienEntity();
        entity.setFullname(nameField.getText());
        if (!dataCRUD.insertEntity(entity)) {
            dialog = new ErrorDialog("Không thể thêm giáo viên mới");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Thêm giáo viên thành công");
        dialog.setVisible(true);
        gvDashboard.updateListTableGVien();
        dispose();
    }
}
