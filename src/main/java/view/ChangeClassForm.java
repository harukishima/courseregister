package view;

import dao.dataCRUD;
import entity.LopEntity;
import entity.SinhvienEntity;

import javax.swing.*;
import javax.tools.Diagnostic;
import java.util.List;

public class ChangeClassForm extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JComboBox classBox;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    private final SinhvienEntity sv;
    private JDialog dialog;
    private GVDashboard gvDashboard;

    public ChangeClassForm(SinhvienEntity sv, GVDashboard gv) {
        this.sv = sv;
        this.gvDashboard = gv;
        init();
    }

    public void init() {
        add(panel);
        setSize(300,200);
        setLocationRelativeTo(null);
        setTitle("Chuyển lớp");
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);
        idField.setText(String.valueOf(sv.getMasv()));
        nameField.setText(sv.getFullname());
        idField.setEditable(false);
        nameField.setEditable(false);
        List<LopEntity> list = dataCRUD.getList(LopEntity.class);
        for(LopEntity lop : list) {
            classBox.addItem(lop.getMalop());
        }
        classBox.setSelectedItem(sv.getClass());
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> confirmChangeClass());
    }

    public void confirmChangeClass() {
        sv.setMalop((Integer) classBox.getSelectedItem());
        dataCRUD.updateEntity(sv, sv.getMasv());
        dialog = new SuccessDialog("Đổi lớp thành công");
        dialog.setVisible(true);
        gvDashboard.updateListTableSV();
        dispose();
    }
}
