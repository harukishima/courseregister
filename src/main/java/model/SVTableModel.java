package model;

import entity.SinhvienEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SVTableModel extends AbstractTableModel {
    List<SinhvienEntity> list;
    String[] columnNames = {"Thay đổi", "Mã SV", "Họ tên", "Giới tính", "Năm nhập học", "Lớp", "Mật khẩu mới"};
    List<Integer> edit = new ArrayList<>();
    List<Integer> pass = new ArrayList<>();

    public SVTableModel(List<SinhvienEntity> list) {
        this.list = list;
    }

    public void setList(List<SinhvienEntity> list) {
        this.list = list;
        edit.clear();
        pass.clear();
        fireTableDataChanged();
    }

    public List<SinhvienEntity> getList() {
        return list;
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
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 2: case 3: case 6:
                return String.class;
            case 1: case 5:
                return Integer.class;
            case 4:
                return Short.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 2 && columnIndex != 5;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        SinhvienEntity sinhvienEntity = list.get(rowIndex);
        boolean edited = false;
        switch (columnIndex) {
            case 2:
                if (!sinhvienEntity.getFullname().equals(aValue)) {
                    sinhvienEntity.setFullname((String) aValue);
                    edited = true;
                }
                break;
            case 3:
                if (!sinhvienEntity.getGioitinh().equals(aValue)) {
                    sinhvienEntity.setGioitinh((String) aValue);
                    edited = true;
                }
                break;
            case 4:
                if (!(sinhvienEntity.getNamnhaphoc().equals(aValue))) {
                    sinhvienEntity.setNamnhaphoc((Short) aValue);
                    edited = true;
                }
                break;
            case 6:
                if (!((String) aValue).isEmpty()) {
                    sinhvienEntity.setPass((String) aValue);
                    edited = true;
                    if (!pass.contains(sinhvienEntity.getMasv())) {
                        pass.add(sinhvienEntity.getMasv());
                    }
                }
            default:
                break;
        }
        if (edited) {
            if (!edit.contains(sinhvienEntity.getMasv())) {
                edit.add(sinhvienEntity.getMasv());
                fireTableCellUpdated(rowIndex, columnIndex);
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
        SinhvienEntity sinhvienEntity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return edit.contains(sinhvienEntity.getMasv())?"*":"";
            case 1:
                return sinhvienEntity.getMasv();
            case 2:
                return sinhvienEntity.getFullname();
            case 3:
                return sinhvienEntity.getGioitinh();
            case 4:
                return sinhvienEntity.getNamnhaphoc();
            case 5:
                return sinhvienEntity.getMalop();
            case 6:
                return pass.contains(sinhvienEntity.getMasv())?
                        StringUtils.repeat('*',sinhvienEntity.getPass().length()):"";
            default:
                return null;

        }
    }
}
