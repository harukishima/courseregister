package model;

import dao.LopDAO;
import entity.LopEntity;
import entity.LopEntityExtended;

import javax.swing.table.AbstractTableModel;
import java.util.List;



public class LopTableModel extends AbstractTableModel {
    private List<LopEntityExtended> list;
    private final String[] columnNames = {"Mã lớp", "Tổng số sinh viên", "Số sinh viên nam", "Số sinh viên nữ"};


    public LopTableModel(List<LopEntity> list) {
        this.list = LopDAO.calListWithSum(list);
    }

    public void setList(List<LopEntity> list) {
        this.list = LopDAO.calListWithSum(list);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
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
        LopEntityExtended lopEntityExtended = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lopEntityExtended.getEntity().getMalop();
            case 1:
                return lopEntityExtended.getFemales() + lopEntityExtended.getMales();
            case 2:
                return lopEntityExtended.getMales();
            case 3:
                return lopEntityExtended.getFemales();
            default:
                return null;
        }
    }
}
