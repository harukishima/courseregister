package view;

import dao.dataCRUD;
import entity.MonhocEntity;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class CreateMonHoc extends JFrame {
    private JTextField nameField;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel panel;
    private JFormattedTextField tinChiField;
    private JDialog dialog;
    private final GVDashboard dashboard;

    public CreateMonHoc(GVDashboard dashboard) throws HeadlessException {
        this.dashboard = dashboard;
        add(panel);
        init();
        setSize(300, 150);
        setLocationRelativeTo(null);
        setTitle("Thêm môn học");
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);
    }

    public void init() {
        tinChiField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tinChiField.selectAll();
                super.mouseClicked(e);
            }
        });
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> createMHAction());
    }

    public void createMHAction() {
        String name = nameField.getText();
        Short soTC;
        try {
            soTC = (Short) tinChiField.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            dialog = new ErrorDialog("Số tín chỉ không hợp lệ");
            dialog.setVisible(true);
            return;
        }
        if (name.equals("")) {
            dialog = new ErrorDialog("Chưa nhập tên môn học");
            dialog.setVisible(true);
            return;
        }
        MonhocEntity monhocEntity = new MonhocEntity();
        monhocEntity.setTenmh(name);
        monhocEntity.setSotinchi(soTC);
        if (!dataCRUD.insertEntity(monhocEntity)) {
            dialog = new ErrorDialog("Không thể thêm môn học");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Thêm môn học thành công");
        dialog.setVisible(true);
        dashboard.updateListTableMH();
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        NumberFormat numberFormatt = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(numberFormatt);
        numberFormatter.setValueClass(Short.class);
        numberFormatter.setAllowsInvalid(false);
        tinChiField = new JFormattedTextField(numberFormatter);
        tinChiField.setValue((short) 0);

    }
}
