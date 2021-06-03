package view;

import dao.dataCRUD;
import entity.SinhvienEntity;
import org.apache.commons.lang3.math.NumberUtils;
import util.HibernateUtils;
import util.NlpUtils;
import util.hashUtils;

import javax.swing.*;
import java.util.Locale;

public class CreateSinhVien extends JFrame {
    private JTextField nameField;
    private JComboBox genderBox;
    private JTextField yearField;
    private JTextField classField;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    private GVDashboard gvDashboard;
    JDialog dialog;

    public CreateSinhVien(GVDashboard gvDashboard) {
        this.gvDashboard = gvDashboard;
        init();
    }

    public CreateSinhVien(int lop, GVDashboard gvDashboard) {
        this.gvDashboard = gvDashboard;
        init();
        classField.setText(String.valueOf(lop));
    }

    public void init() {
        add(panel);
        setTitle("Thêm sinh viên");
        getRootPane().setDefaultButton(OKButton);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        classField.setEditable(false);
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> createSV());

    }

    public void createSV() {
//        if (!nameField.getText().isEmpty() || !NumberUtils.isCreatable(yearField.getText())) {
//            dialog = new ErrorDialog("Không đúng định dạng");
//            dialog.setVisible(true);
//            return;
//        }
        SinhvienEntity sinhvienEntity = new SinhvienEntity();
        sinhvienEntity.setFullname(nameField.getText());
        String pass = NlpUtils.removeAccent(nameField.getText().replaceAll(" ", "").toLowerCase(Locale.ROOT));
        sinhvienEntity.setPass(hashUtils.hashPassword(pass));
        sinhvienEntity.setNamnhaphoc(Short.valueOf(yearField.getText()));
        sinhvienEntity.setGioitinh((String) genderBox.getSelectedItem());
        if (!classField.getText().isEmpty()) {
            sinhvienEntity.setMalop(Integer.valueOf(classField.getText()));
        }
        if (!dataCRUD.insertEntity(sinhvienEntity)) {
            dialog = new ErrorDialog("Không thể thêm");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Thêm SV thành công");
        dialog.setVisible(true);
        gvDashboard.updateListTableSV();
        dispose();
    }
}
