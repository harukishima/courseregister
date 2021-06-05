package model;

import entity.HocphanEntity;
import util.HocphanUtils;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.List;

public class HPTableModel extends AbstractTableModel {
    protected List<HocphanEntity> list;
    protected String[] columnNames = {"Mã HP", "Mã MH", "Tên MH", "Số tín chỉ", "Giáo viên LT", "Phòng học", "Ngày học", "Ca",
            "Học kì", "Năm học", "Slot", "Ngày bắt đầu"};

    public HPTableModel(List<HocphanEntity> list) {
        this.list = list;
    }

    public void setList(List<HocphanEntity> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public List<HocphanEntity> getList() {
        return list;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 1: case 9:
                return Integer.class;
            case 2: case 4: case 5: case 6: case 7: case 8:
                return String.class;
            case 3: case 10:
                return Short.class;
            case 11:
                return Date.class;
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HocphanEntity entity = list.get(rowIndex);
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
                return HocphanUtils.getNgayTrongTuan((int) entity.getNgaytrongtuan());
            case 7:
                return HocphanUtils.getCa((int) entity.getCa());
            case 8:
                return entity.getTenhk();
            case 9:
                return entity.getNamhoc();
            case 10:
                return entity.getSlot();
            case 11:
                return entity.getNgaybatdau();
            default:
                return null;
        }
    }
}
