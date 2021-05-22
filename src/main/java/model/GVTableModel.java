package model;

import entity.GiaovuEntity;
import org.apache.commons.lang3.StringUtils;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class GVTableModel extends AbstractTableModel {
    List<GiaovuEntity> list;
    final String[] columnNames = {"Thay đổi", "Mã GV", "Họ tên", "Mật khẩu mới", "Xoá"};
    List<Integer> delete = new ArrayList<>();
    List<Integer> edit = new ArrayList<>();
    List<Integer> pass = new ArrayList<>();

    public GVTableModel(List<GiaovuEntity> list) {
        this.list = list;
    }

    public void setList(List<GiaovuEntity> list) {
        this.list = list;
        delete.clear();
        edit.clear();
        pass.clear();
        fireTableDataChanged();
    }

    public List<Integer> getDelete() {
        return delete;
    }

    public List<Integer> getEdit() {
        return edit;
    }

    public List<Integer> getPass() {
        return pass;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
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
        GiaovuEntity giaovuEntity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return (edit.contains(giaovuEntity.getMagv())?"*":"");
            case 1:
                return giaovuEntity.getMagv();
            case 2:
                return giaovuEntity.getFullname();
            case 3:
                return pass.contains(giaovuEntity.getMagv())? StringUtils.repeat('*', giaovuEntity.getPass().length()) :"";
            case 4:
                return delete.contains(giaovuEntity.getMagv());
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 2;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        GiaovuEntity giaovuEntity = list.get(rowIndex);
        if (columnIndex == 2) {
            String oldName = giaovuEntity.getFullname();
            if (!oldName.equals(aValue)) {
                giaovuEntity.setFullname((String) aValue);
                if (!edit.contains(giaovuEntity.getMagv())) {
                    edit.add(giaovuEntity.getMagv());
                    fireTableCellUpdated(rowIndex, 0);
                }
            }
        }
        if (columnIndex == 3) {
            String oldPass = giaovuEntity.getPass();
            if (!oldPass.equals(aValue)) {
                giaovuEntity.setPass((String) aValue);
                if (!pass.contains(giaovuEntity.getMagv())) {
                    if (!edit.contains(giaovuEntity.getMagv())) {
                        edit.add(giaovuEntity.getMagv());
                        //fireTableCellUpdated(rowIndex, 0);
                    }
                    pass.add(giaovuEntity.getMagv());
                    fireTableCellUpdated(rowIndex, 0);
                }
            }
        }
        if (columnIndex == 4) {
            if (delete.contains(giaovuEntity.getMagv())) {
                delete.remove((Integer) giaovuEntity.getMagv());
            } else {
                delete.add( giaovuEntity.getMagv());
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 1: case 2: case 3:
                return String.class;
            case 4:
                return Boolean.class;
            default:
                return null;
        }
    }
}
