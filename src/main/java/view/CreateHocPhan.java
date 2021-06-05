package view;

import com.github.lgooddatepicker.components.DatePicker;
import dao.dataCRUD;
import entity.GiaovienEntity;
import entity.HockiEntity;
import entity.HocphanEntity;
import entity.MonhocEntity;
import util.HocphanUtils;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public class CreateHocPhan extends JFrame {
    private JPanel panel;
    private JComboBox mhBox;
    private JComboBox giaovienBox;
    private JTextField phongHocField;
    private JComboBox ngayhocBox;
    private JComboBox cahocBox;
    private JTextField hockiField;
    private JTextField namhocField;
    private JButton OKButton;
    private JButton cancelButton;
    private JTextField mamhField;
    private JTextField tinchiField;
    private JFormattedTextField slotField;
    private DatePicker startDate;
    private GVDashboard gvDashboard;
    private HockiEntity hockiEntity;
    private JDialog dialog;
    private List<MonhocEntity> monhocEntityList;
    private List<GiaovienEntity> giaovienEntityList;

    public CreateHocPhan(GVDashboard gvDashboard, HockiEntity hockiEntity) throws HeadlessException {
        this.gvDashboard = gvDashboard;
        this.hockiEntity = hockiEntity;
        initForm();
    }

    public void initForm() {
        add(panel);
        setTitle("Thêm học phần");
        setSize(700,500);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(OKButton);
        setVisible(true);

        monhocEntityList = dataCRUD.getList(MonhocEntity.class);
        giaovienEntityList = dataCRUD.getList(GiaovienEntity.class);
        for(MonhocEntity entity : monhocEntityList) {
            mhBox.addItem(entity.getTenmh());
        }
        for(GiaovienEntity entity : giaovienEntityList) {
            giaovienBox.addItem(entity.getFullname());
        }
        mhBox.setSelectedItem(null);
        giaovienBox.setSelectedItem(null);
        hockiField.setText(hockiEntity.getTenhk());
        namhocField.setText(String.valueOf(hockiEntity.getNamhoc()));
        mhBox.addActionListener(e -> {
            int index = mhBox.getSelectedIndex();
            mamhField.setText(String.valueOf(monhocEntityList.get(index).getMamh()));
            tinchiField.setText(String.valueOf(monhocEntityList.get(index).getSotinchi()));
        });
        cancelButton.addActionListener(e -> dispose());
        OKButton.addActionListener(e -> submitNewHP());
        slotField.setValue(0);
        slotField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                slotField.selectAll();
            }
        });
        startDate.setDate(LocalDate.now());
    }

    private void submitNewHP() {
        HocphanEntity entity = new HocphanEntity();
        try {
            entity.setMamh(monhocEntityList.get(mhBox.getSelectedIndex()).getMamh());
            entity.setMagvlt(giaovienEntityList.get(giaovienBox.getSelectedIndex()).getMagv());
            entity.setNgaytrongtuan((short) HocphanUtils.getIndexOfDate((String) ngayhocBox.getSelectedItem()));
            entity.setCa((short) HocphanUtils.getIndexOfCaHoc((String) cahocBox.getSelectedItem()));
            entity.setPhonghoc(phongHocField.getText());
            entity.setTenhk(hockiEntity.getTenhk());
            entity.setNamhoc(hockiEntity.getNamhoc());
            entity.setSlot((Short) slotField.getValue());
            entity.setNgaybatdau(new Date(startDate.getDate().atStartOfDay().toEpochSecond(ZoneOffset.ofHours(7)) * 1000L));
            dataCRUD.insertEntity(entity);
        } catch (Exception e) {
            e.printStackTrace();
            dialog = new ErrorDialog("Không thể thêm");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Thêm thành công");
        dialog.setVisible(true);
        gvDashboard.updateListTableHP();
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ngayhocBox = new JComboBox(HocphanUtils.weekDay);
        cahocBox = new JComboBox(HocphanUtils.caHoc);
        NumberFormatter numberFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
        numberFormatter.setValueClass(Short.class);
        numberFormatter.setAllowsInvalid(false);
        slotField = new JFormattedTextField(numberFormatter);
    }
}
