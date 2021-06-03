package view;

import com.github.lgooddatepicker.components.DateTimePicker;
import dao.dataCRUD;
import entity.HockiEntity;
import entity.KidangkihocphanEntity;
import org.postgresql.util.PGTimestamp;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneOffset;

public class CreateKDK extends JFrame {
    private JTextField hkField;
    private JTextField yearField;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel panel;
    private DateTimePicker startDateTime;
    private DateTimePicker endDateTime;
    private final GVDashboard gvDashboard;
    private final HockiEntity currentHK;
    private JDialog dialog;

    public CreateKDK(GVDashboard gvDashboard, HockiEntity currentHK) throws HeadlessException {
        this.gvDashboard = gvDashboard;
        this.currentHK = currentHK;
        init();
    }

    public void init() {
        add(panel);
        setTitle("Thêm kì đăng kí");
        setSize(400,250);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);

        hkField.setText(currentHK.getTenhk());
        yearField.setText(String.valueOf(currentHK.getNamhoc()));
        hkField.setEditable(false);
        yearField.setEditable(false);
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> submitKDK());
        startDateTime.setDateTimePermissive(currentHK.getNgaybatdau().toLocalDateTime());
        endDateTime.setDateTimePermissive(currentHK.getNgayketthuc().toLocalDateTime());
    }

    public void submitKDK() {
        KidangkihocphanEntity entity = new KidangkihocphanEntity();
        entity.setTenhk(currentHK.getTenhk());
        entity.setNamhoc(currentHK.getNamhoc());
        PGTimestamp startDate, endDate; ;
        try {
            startDate = new PGTimestamp(startDateTime.getDateTimeStrict().toEpochSecond(ZoneOffset.ofHours(7)) * 1000L);
            endDate = new PGTimestamp(endDateTime.getDateTimeStrict().toEpochSecond(ZoneOffset.ofHours(7)) * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
            dialog = new ErrorDialog("Chưa nhập ngày giờ");
            dialog.setVisible(true);
            return;
        }
        entity.setNgaybatdau(startDate);
        entity.setNgayketthuc(endDate);
        dataCRUD.insertEntity(entity);
        dialog = new SuccessDialog("Tạo kì đăng kí mới thành công");
        dialog.setVisible(true);
        gvDashboard.updateListTableKDK();
        dispose();
    }
}
