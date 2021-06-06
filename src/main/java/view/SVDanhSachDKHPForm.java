package view;

import dao.HockiDAO;
import dao.SvdkhpDAO;
import entity.HockiEntity;
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
    private JComboBox hkBox;
    private JComboBox yearBox;
    private JButton xemButton;
    private HPTableModel hpTableModel;
    private final SVDashboard svDashboard;
    private final SinhvienEntity sinhvienEntity;

    public SVDanhSachDKHPForm(SVDashboard svDashboard, SinhvienEntity sinhvienEntity) throws HeadlessException {
        this.svDashboard = svDashboard;
        this.sinhvienEntity = sinhvienEntity;
        initTable();
        initForm();
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
        hkBox.addItem("HK1");
        hkBox.addItem("HK2");
        hkBox.addItem("HK3");
        List<Integer> listYear = HockiDAO.findAvailableYear();
        for(Integer i : listYear) {
            yearBox.addItem(i);
        }
        HockiEntity curHK = HockiDAO.getCurrentHK();
        hkBox.setSelectedItem(curHK.getTenhk().trim());
        yearBox.setSelectedItem(curHK.getNamhoc());
        xemButton.addActionListener(e -> {
            HockiEntity hk = new HockiEntity();
            hk.setNamhoc((Integer) yearBox.getSelectedItem());
            hk.setTenhk((String) hkBox.getSelectedItem());
            searchHP(hk);
        });
        xemButton.doClick(0);
    }

    private void initTable() {
        List<HocphanEntity> hocphanEntityList = new ArrayList<>();
        hpTableModel = new HPTableModel(hocphanEntityList);
        HPTable.setModel(hpTableModel);
    }

    private void searchHP(HockiEntity hk) {
        List<SvdkhpEntity> svdkhpEntityList = SvdkhpDAO.findDKBySVandHK(sinhvienEntity.getMasv(), hk);
        List<HocphanEntity> hocphanEntityList = new ArrayList<>();
        for (SvdkhpEntity entity : svdkhpEntityList) {
            hocphanEntityList.add(entity.getHocphanByIdhocphan());
        }
        hpTableModel.setList(hocphanEntityList);
    }

}
