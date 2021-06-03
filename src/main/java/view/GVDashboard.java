package view;

import dao.*;
import entity.*;
import model.*;
import org.example.App;
import util.hashUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class GVDashboard extends JFrame {
    private JTabbedPane tabbedPane1;

    private JTextField searchGVField;
    private JButton searchGVButton;
    private JButton addGVButton;
    private JButton deleteGVButton;
    private JTable GVTable;
    private JButton logOutButton;
    private JPanel panel;
    private JButton editGVButton;

    private JTextField searchMHField;
    private JButton searchMHButton;
    private JTable MHTable;
    private JButton editMHButton;
    private JButton addMHButton;
    private JButton deleteMHButton;

    private JTable HKTable;
    private JButton addHKButton;
    private JButton deleteHKButton;
    private JButton setCurrentHKButton;

    private JTable LopTable;
    private JButton addLopButton;
    private JButton deleteLopButton;

    private JTable SVTable;
    private JTextField svSearchField;
    private JComboBox classBox;
    private JButton addSVButton;
    private JButton editSVButton;
    private JButton searchButton;
    private JButton changeClassButton;

    private GVTableModel gvTableModel;
    private MHTableModel mhTableModel;
    private HKTableModel hkTableModel;
    private LopTableModel lopTableModel;
    private SVTableModel svTableModel;
    private JDialog dialog;

    public GVDashboard() {
        add(panel);
        init();
        setSize(800,600);
        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        logOutButton.addActionListener(e -> {
            App.currentGV = null;
            App.gvDashboard = null;
            App.giaoVuLogIn = new GiaoVuLogIn();
            dispose();
        });
        initGVTab();
        initMHTab();
        initHKTab();
        initClassTab();
        initSVTab();
    }

    public void initGVTab() {
        List<GiaovuEntity> list = dataCRUD.getList(GiaovuEntity.class);
        gvTableModel = new GVTableModel(list);
        GVTable.setModel(gvTableModel);
        addGVButton.addActionListener(e -> openCreateGVForm());
        searchGVButton.addActionListener(this::searchGV);
        searchGVField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //searchGV(null);
                    searchGVButton.doClick(0);
                }
                super.keyPressed(e);
            }
        });
        deleteGVButton.addActionListener(e -> {
            if (gvTableModel.getDelete().isEmpty()) {
                dialog = new ErrorDialog("Chưa chọn GV nào");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá các GV trên?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                deleteGV();
            }
        });
        editGVButton.addActionListener(e -> {
            if (gvTableModel.getEdit().isEmpty()) {
                dialog = new ErrorDialog("Không có gì để sửa");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa các GV trên?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                saveEditGV();
            }
        });
    }

    public void initMHTab() {
        List<MonhocEntity> listMH = dataCRUD.getList(MonhocEntity.class);
        mhTableModel = new MHTableModel(listMH);
        MHTable.setModel(mhTableModel);
        addMHButton.addActionListener(e -> new CreateMonHoc(this));
        searchMHButton.addActionListener(this::searchMH);
        searchMHField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchMHButton.doClick(0);
                }
                super.keyPressed(e);
            }
        });
        editMHButton.addActionListener(e -> {
            if (mhTableModel.getEdit().isEmpty()) {
                dialog = new ErrorDialog("Không có gì để sửa");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa các MH trên?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                saveEditMH();
            }
        });
        deleteMHButton.addActionListener(e -> {
            if (mhTableModel.getDelete().isEmpty()) {
                dialog = new ErrorDialog("Chưa chọn MH nào");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá các MH trên?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                deleteMH();
            }
        });
    }

    public void initHKTab() {
        List<HockiEntity> listHK = dataCRUD.getListOrder(HockiEntity.class, "order by tenhk asc, namhoc asc");
        hkTableModel = new HKTableModel(listHK);
        HKTable.setModel(hkTableModel);
        addHKButton.addActionListener(e -> new CreateHocKi(this));
        setCurrentHKButton.addActionListener(e -> setCurrentHK());
        deleteHKButton.addActionListener(e -> {
            int row = HKTable.getSelectedRow();
            if (row < 0) {
                dialog = new ErrorDialog("Chưa chọn HK nào");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                deleteHK();
            }
        });
    }

    public void initSVTab() {
        List<LopEntity> lopEntityList = dataCRUD.getList(LopEntity.class);
        classBox.addItem("*");
        for (LopEntity e : lopEntityList) {
            classBox.addItem(e.getMalop());
        }
        classBox.setSelectedItem("*");
        List<SinhvienEntity> sinhvienEntityList = dataCRUD.getList(SinhvienEntity.class);
        svTableModel = new SVTableModel(sinhvienEntityList);
        JComboBox<String> gender = new JComboBox<>();
        gender.addItem("NAM");
        gender.addItem("NU");
        SVTable.setModel(svTableModel);
        SVTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(gender));
        addSVButton.addActionListener(e -> {
            addNewSV();
        });
        editSVButton.addActionListener(e -> {
            if (svTableModel.getEdit().isEmpty()) {
                dialog = new ErrorDialog("Không có gì để sửa");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa các SV trên?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                saveEditSV();
            }
        });
        changeClassButton.addActionListener(e -> {
            if (SVTable.getSelectedRow() < 0) {
                dialog = new ErrorDialog("Chưa chọn sinh viên");
                dialog.setVisible(true);
                return;
            }
            new ChangeClassForm(svTableModel.getList().get(SVTable.getSelectedRow()), this);
        });
    }

    public void initClassTab() {
        List<LopEntity> list = dataCRUD.getList(LopEntity.class);
        lopTableModel = new LopTableModel(list);
        LopTable.setModel(lopTableModel);
        deleteLopButton.addActionListener(e -> {
            int row = LopTable.getSelectedRow();
            if (row < 0) {
                dialog = new ErrorDialog("Chưa chọn lớp nào");
                dialog.setVisible(true);
                return;
            }
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xoá?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                deleteLop(row);
            }
        });
        addLopButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm lớp?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                addNewClass();
            }
        });
    }

    public void searchGV(ActionEvent e) {
        updateTableGV(GiaovuDAO.findGV(searchGVField.getText()));
    }

    public void searchMH(ActionEvent e) {
        updateTableMH(MonhocDAO.findMH(searchMHField.getText()));
    }

    public void updateListTableGV() {
        List<GiaovuEntity> list = dataCRUD.getList(GiaovuEntity.class);
        updateTableGV(list);
        searchGVField.setText("");
    }

    public void updateListTableMH() {
        List<MonhocEntity> list = dataCRUD.getList(MonhocEntity.class);
        updateTableMH(list);
        searchMHField.setText("");
    }

    public void updateListTableHK() {
        List<HockiEntity> list = dataCRUD.getListOrder(HockiEntity.class, "order by tenhk asc, namhoc asc");
        updateTableHK(list);
    }

    public void updateListTableLop() {
        List<LopEntity> list = dataCRUD.getListOrder(LopEntity.class, "order by malop asc");
        updateTableLop(list);
    }

    public void updateListTableSV() {
        List<SinhvienEntity> list;
        try {
            int classID = (int) classBox.getSelectedItem();
            list = (List<SinhvienEntity>) LopDAO.getByIdLoadAll(classID).getSinhviensByMalop();
        } catch (Exception e) {
            list = dataCRUD.getListOrder(SinhvienEntity.class, "order by masv asc");
        }
        updateTableSV(list);
        svSearchField.setText("");
    }

    public void updateTableGV(List<GiaovuEntity> list) {
        gvTableModel.setList(list);
    }

    public void updateTableMH(List<MonhocEntity> list) {
        mhTableModel.setList(list);
    }

    public void updateTableHK(List<HockiEntity> list) {
        hkTableModel.setList(list);
    }

    public void updateTableLop(List<LopEntity> list) {
        lopTableModel.setList(list);
    }

    public void updateTableSV(List<SinhvienEntity> list) {
        svTableModel.setList(list);
    }

    public void openCreateGVForm() {
        new CreateGiaoVu(this);
    }

    public void addNewClass() {
        LopEntity lopEntity = new LopEntity();

        if (!dataCRUD.insertEntity(lopEntity)) {
            dialog = new SuccessDialog("Không thể thêm lớp mới");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Thêm lớp mới thành công");
        dialog.setVisible(true);
        updateListTableLop();
    }

    public void addNewSV() {
        if(Objects.equals(classBox.getSelectedItem(), "*")) {
            new CreateSinhVien(this);
        } else {
            new CreateSinhVien((Integer) classBox.getSelectedItem(), this);
        }
    }

    public void saveEditGV() {
        List<GiaovuEntity> list = gvTableModel.getList();
        List<Integer> edit = gvTableModel.getEdit();
        List<Integer> pass = gvTableModel.getPass();
        for(GiaovuEntity gv : list) {
            if (pass.contains(gv.getMagv())) {
                gv.setPass(hashUtils.hashPassword(gv.getPass()));
            }
            if (edit.contains(gv.getMagv())) {
                dataCRUD.updateEntity(gv, gv.getMagv());
            }
        }
        dialog = new SuccessDialog("Sửa thành công");
        dialog.setVisible(true);
        updateListTableGV();
    }

    public void saveEditMH() {
        List<MonhocEntity> list = mhTableModel.getList();
        List<Integer> edit = mhTableModel.getEdit();
        for(MonhocEntity mh : list) {
            if (edit.contains(mh.getMamh())) {
                dataCRUD.updateEntity(mh, mh.getMamh());
            }
        }
        dialog = new SuccessDialog("Sửa thành công");
        dialog.setVisible(true);
        updateListTableMH();
    }

    public void saveEditSV() {
        List<SinhvienEntity> list = svTableModel.getList();
        List<Integer> edit = svTableModel.getEdit();
        List<Integer> pass = svTableModel.getPass();
        for (SinhvienEntity sv : list) {
            if (pass.contains(sv.getMasv())) {
                sv.setPass(hashUtils.hashPassword(sv.getPass()));
            }
            if (edit.contains(sv.getMasv())) {
                dataCRUD.updateEntity(sv, sv.getMasv());
            }
        }
        dialog = new SuccessDialog("Cập nhật thành công");
        dialog.setVisible(true);
        updateListTableSV();
    }

    public void deleteGV() {
        List<GiaovuEntity> list = gvTableModel.getList();
        List<Integer> delete = gvTableModel.getDelete();
        for (GiaovuEntity gv : list) {
            if (delete.contains(gv.getMagv())) {
                dataCRUD.deleteEntityById(GiaovuEntity.class, gv.getMagv());
            }
        }
        dialog = new SuccessDialog("Xoá thành công");
        dialog.setVisible(true);
        updateListTableGV();
    }

    public void deleteMH() {
        List<MonhocEntity> list = mhTableModel.getList();
        List<Integer> delete = mhTableModel.getDelete();
        for (MonhocEntity mh : list) {
            if (delete.contains(mh.getMamh())) {
                dataCRUD.deleteEntityById(MonhocEntity.class, mh.getMamh());
            }
        }
        dialog = new SuccessDialog("Xoá thành công");
        dialog.setVisible(true);
        updateListTableMH();
    }

    public void deleteHK() {
        int row = HKTable.getSelectedRow();
        String tenhk = (String) HKTable.getValueAt(row, 0);
        int nam = (int) HKTable.getValueAt(row, 1);
        if (!dataCRUD.deleteEntity(HockiDAO.getHK(tenhk, nam))) {
            dialog = new ErrorDialog("Không thể xoá HK");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Xoá HK thành công");
        dialog.setVisible(true);
        updateListTableHK();
    }

    public void deleteLop(int row) {
        int id = (int) LopTable.getValueAt(row, 0);
        if (!LopDAO.checkClassEmpty(id)) {
            dialog = new ErrorDialog("Chỉ có thể xoá lớp không có SV");
            dialog.setVisible(true);
            return;
        }
        if (!LopDAO.deleteLop(id)) {
            dialog = new ErrorDialog("Không thể xoá lớp");
            dialog.setVisible(true);
            return;
        }
        dialog = new SuccessDialog("Xoá lớp thành công");
        dialog.setVisible(true);
        updateListTableLop();
    }

    public void setCurrentHK() {
        int row = HKTable.getSelectedRow();
        String tentk = (String) HKTable.getValueAt(row, 0);
        int nam = (int) HKTable.getValueAt(row, 1);
        if (!HockiDAO.setCurrentHK(tentk, nam)) {
            dialog = new ErrorDialog("Không thể đặt làm HK hiện tại");
            dialog.setVisible(true);
        }
        updateListTableHK();
    }

}
