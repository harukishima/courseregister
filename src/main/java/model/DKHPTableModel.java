package model;

import entity.HocphanEntity;
import util.HocphanUtils;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;

public class DKHPTableModel extends AbstractTableModel {
    private DKHPController controller;
    private String[] columnNames = {"Mã HP", "Mã MH", "Tên MH", "Số tín chỉ", "Giáo viên LT", "Phòng học", "Ngày học", "Ca",
            "Học kì", "Năm học", "Slot", "Ngày bắt đầu", "Đăng kí"};

    public DKHPTableModel(DKHPController controller) {
        this.controller = controller;
    }

    public DKHPController getController() {
        return controller;
    }

    public void setController(DKHPController controller) {
        this.controller = controller;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 1:
                return Integer.class;
            case 2: case 4: case 5: case 6: case 7: case 8: case 10:
                return String.class;
            case 3: case 9:
                return Short.class;
            case 11:
                return Date.class;
            case 12:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return controller.getListHPSize();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 12;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        HocphanEntity hp = controller.getHP(rowIndex);
        if (controller.isInDk(hp.getIdhocphan())) {
            controller.removeFromDk(hp.getIdhocphan());
        } else {
            controller.addToDk(hp);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HocphanEntity entity = controller.getHP(rowIndex);
        switch (columnIndex) {
            case 0:
                return entity.getIdhocphan();
            case 1:
                return entity.getMamh();
            case 2:
                return entity.getMonhocByMamh().getTenmh();
            case 3:
                return entity.getMonhocByMamh().getSotinchi();
            case 4:
                return entity.getGiaovienByMagvlt().getFullname();
            case 5:
                return entity.getPhonghoc();
            case 6:
                return HocphanUtils.getNgayTrongTuan(entity.getNgaytrongtuan());
            case 7:
                return HocphanUtils.getCa(entity.getCa());
            case 8:
                return entity.getTenhk();
            case 9:
                return entity.getNamhoc();
            case 10:
                return controller.getSlotOfHP(entity.getIdhocphan());
            case 11:
                return entity.getNgaybatdau();
            case 12:
                return controller.isInDk(entity.getIdhocphan());
            default:
                return null;
        }
    }
}
