package view;

import dao.SvdkhpDAO;
import entity.HocphanEntity;
import entity.SinhvienEntity;
import entity.SvdkhpEntity;
import model.HPTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class SVDanhSachDKHPForm extends JFrame {
    private JPanel panel1;
    private JTable HPTable;
    private final SVDashboard svDashboard;
    private final SinhvienEntity sinhvienEntity;

    public SVDanhSachDKHPForm(SVDashboard svDashboard, SinhvienEntity sinhvienEntity) throws HeadlessException {
        this.svDashboard = svDashboard;
        this.sinhvienEntity = sinhvienEntity;
        initForm();
        initTable();
    }

    private void initForm() {
        add(panel1);
        setTitle("Danh sách học phần đã đăng kí");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                svDashboard.setVisible(true);
            }
        });
    }

    private void initTable() {
        List<SvdkhpEntity> svdkhpEntityList = SvdkhpDAO.getByIdSV(sinhvienEntity.getMasv());
        List<HocphanEntity> hocphanEntityList = new ArrayList<>();
        for (SvdkhpEntity entity : svdkhpEntityList) {
            hocphanEntityList.add(entity.getHocphanByIdhocphan());
        }
        HPTableModel hpTableModel = new HPTableModel(hocphanEntityList);
        HPTable.setModel(hpTableModel);
    }

}
