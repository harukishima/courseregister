package view;

import com.github.lgooddatepicker.components.DatePicker;
import dao.dataCRUD;
import entity.HockiEntity;
import org.postgresql.util.PGTimestamp;

import javax.swing.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Calendar;

public class CreateHocKi extends JFrame {
    private JComboBox tenHKBox;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel panel;
    private JTextField namHocField;
    private DatePicker startDateField;
    private DatePicker endDateField;
    private JDialog dialog;
    GVDashboard gvDashboard;

    public CreateHocKi(GVDashboard gvDashboard) {
        add(panel);
        this.gvDashboard = gvDashboard;
        init();
        getRootPane().setDefaultButton(OKButton);
        setSize(400, 300);
        setTitle("Tạo học kì");
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void init() {
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> submitNewHK());
    }

    public void submitNewHK() {
        int namHoc;
        try {
            namHoc = Integer.parseInt(namHocField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            dialog = new ErrorDialog("Năm học sai định dạng");
            dialog.setVisible(true);
            return;
        }
        HockiEntity hockiEntity = new HockiEntity();
        hockiEntity.setTenhk((String) tenHKBox.getSelectedItem());
        hockiEntity.setNamhoc(namHoc);
        hockiEntity.setHkhientai(false);
        try {
            System.out.println(startDateField.getDate().toString());
            PGTimestamp timestamp = new PGTimestamp(startDateField.getDate().atStartOfDay().toEpochSecond(ZoneOffset.ofHours(7)) * 1000L);
            System.out.println(timestamp);
            hockiEntity.setNgaybatdau(timestamp);
            hockiEntity.setNgayketthuc(new PGTimestamp(endDateField.getDate().atStartOfDay().toEpochSecond(ZoneOffset.ofHours(7)) * 1000L));
        } catch (Exception e) {
            e.printStackTrace();
            dialog = new ErrorDialog("Chưa nhập ngày");
            dialog.setVisible(true);
            return;
        }

        dataCRUD.insertEntity(hockiEntity);
        dialog = new SuccessDialog("Thêm HK thành công");
        dialog.setVisible(true);
        gvDashboard.updateListTableHK();
        dispose();

    }


}
