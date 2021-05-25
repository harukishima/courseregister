package model;

import entity.MonhocEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MHTableModel extends AbstractTableModel {
    List<MonhocEntity> list;
    String[] columnName = {"Thay đổi", "Mã MH", "Tên MH", "Số tín chỉ", "Xoá"};
    List<Integer> edit = new ArrayList<>();
    List<Integer> delete = new ArrayList<>();

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public int findColumn(String columnName) {
        return columnName.indexOf(columnName);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return Integer.class;
            case 0: case 2:
                return String.class;
            case 3:
                return Short.class;
            case 4:
                return Boolean.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MonhocEntity monhocEntity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return edit.contains(monhocEntity.getMamh())?"*":"";
            case 1:
                return monhocEntity.getMamh();
            case 2:
                return monhocEntity.getTenmh();
            case 3:
                return monhocEntity.getSotinchi();
            case 4:
                return delete.contains(monhocEntity.getMamh());
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex >= 2) {
            MonhocEntity monhocEntity = list.get(rowIndex);
            if (columnIndex != 4) {
                if (columnIndex == 2) {
                    monhocEntity.setTenmh((String) aValue);
                }
                if (columnIndex == 3) {
                    monhocEntity.setSotinchi((Short) aValue);
                }
                if (!edit.contains(monhocEntity.getMamh())) {
                    edit.add(monhocEntity.getMamh());
                }
            } else {
                if (delete.contains(monhocEntity.getMamh())) {
                    delete.remove((Integer) monhocEntity.getMamh());
                } else {
                    delete.add(monhocEntity.getMamh());
                }
            }

        }
    }

    public List<MonhocEntity> getList() {
        return list;
    }

    public List<Integer> getEdit() {
        return edit;
    }

    public List<Integer> getDelete() {
        return delete;
    }

    public void setList(List<MonhocEntity> list) {
        this.list = list;
        delete.clear();
        edit.clear();
        fireTableDataChanged();
    }

    public MHTableModel(List<MonhocEntity> list) {
        this.list = list;
    }
}
