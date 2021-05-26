package model;

import entity.HockiEntity;

import java.sql.Timestamp;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class HKTableModel extends AbstractTableModel {
    List<HockiEntity> list;
    //List<Integer> delete = new ArrayList<>();
    String[] columnNames = {"Tên học kì", "Năm học", "Ngày bắt đầu", "Ngày kết thúc", "Hiện tại?"};

    public HKTableModel(List<HockiEntity> list) {
        this.list = list;
    }

    public List<HockiEntity> getList() {
        return list;
    }

    public void setList(List<HockiEntity> list) {
        this.list = list;
        fireTableDataChanged();
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
        HockiEntity hockiEntity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return hockiEntity.getTenhk();
            case 1:
                return hockiEntity.getNamhoc();
            case 2:
                return hockiEntity.getNgaybatdau();
            case 3:
                return hockiEntity.getNgayketthuc();
            case 4:
                return hockiEntity.getHkhientai();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
            case 3:
                return Timestamp.class;
            case 4:
                return Boolean.class;
            default:
                return null;
        }
    }
}
