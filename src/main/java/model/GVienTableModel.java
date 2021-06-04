package model;

import entity.GiaovienEntity;
import entity.GiaovuEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GVienTableModel extends AbstractTableModel {
    List<GiaovienEntity> list;
    String[] columnNames = {"Thay đồi", "MS", "Họ tên"};
    List<Integer> edit = new ArrayList<>();

    public GVienTableModel(List<GiaovienEntity> list) {
        this.list = list;
    }

    public void setList(List<GiaovienEntity> list) {
        this.list = list;
        edit.clear();
        fireTableDataChanged();
    }

    public List<GiaovienEntity> getList() {
        return list;
    }

    public List<Integer> getEdit() {
        return edit;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 2:
                return String.class;
            case 1:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (!list.get(rowIndex).getFullname().equals(aValue)) {
            list.get(rowIndex).setFullname((String) aValue);
            if (!edit.contains(list.get(rowIndex).getMagv())) {
                edit.add(list.get(rowIndex).getMagv());
                fireTableCellUpdated(rowIndex, 0);
            }
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
        GiaovienEntity entity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return edit.contains(entity.getMagv())?"*":"";
            case 1:
                return entity.getMagv();
            case 2:
                return entity.getFullname();
            default:
                return null;
        }
    }
}
