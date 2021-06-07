package view;

import dao.dataCRUD;
import entity.SinhvienEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AccountInfoFrame extends JFrame {
    private JTextField mssvField;
    private JTextField nameField;
    private JTextField yearField;
    private JTextField classField;
    private JComboBox genderBox;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    private SinhvienEntity sinhvienEntity;
    private final SVDashboard svDashboard;

    public AccountInfoFrame(SinhvienEntity sinhvienEntity, SVDashboard svDashboard) throws HeadlessException {
        this.sinhvienEntity = sinhvienEntity;
        this.svDashboard = svDashboard;
        initForm();
    }

    private void initForm() {
        add(panel);
        setTitle("Thông tin sinh viên");
        setSize(400, 500);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);
        genderBox.addItem("NAM");
        genderBox.addItem("NU");
        genderBox.setSelectedItem(sinhvienEntity.getGioitinh().trim());
        mssvField.setText(String.valueOf(sinhvienEntity.getMasv()));
        nameField.setText(sinhvienEntity.getFullname());
        yearField.setText(String.valueOf(sinhvienEntity.getNamnhaphoc()));
        classField.setText(String.valueOf(sinhvienEntity.getMalop()));
        cancelButton.addActionListener(e -> {
            svDashboard.setVisible(true);
            dispose();
        });
        OKButton.addActionListener(e -> updateInfo());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                svDashboard.setVisible(true);
            }
        });
    }

    private void updateInfo()  {
        if (!sinhvienEntity.getGioitinh().equals(genderBox.getSelectedItem()) ||
                !sinhvienEntity.getFullname().equals(nameField.getText())) {
            sinhvienEntity.setGioitinh((String) genderBox.getSelectedItem());
            sinhvienEntity.setFullname((nameField.getText()));
            dataCRUD.updateEntity(sinhvienEntity, sinhvienEntity.getMasv());
            (new SuccessDialog("Cập nhật thông tin thành công")).setVisible(true);
        }
        svDashboard.setVisible(true);
        dispose();
    }
}
