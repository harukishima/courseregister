package model;

import entity.KidangkihocphanEntity;

import javax.swing.table.AbstractTableModel;
import java.sql.Timestamp;
import java.util.List;

public class KDKTableModel extends AbstractTableModel {
    List<KidangkihocphanEntity> list;
    String[] columnNames = {"Id", "Học kì", "Năm", "Ngày bắt đầu", "Ngày kết thúc"};

    public KDKTableModel(List<KidangkihocphanEntity> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Short.class;
            case 3: case 4:
                return String.class;
            default:
                return null;
        }
    }

    public void setList(List<KidangkihocphanEntity> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public List<KidangkihocphanEntity> getList() {
        return list;
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
        KidangkihocphanEntity entity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entity.getIdkidk();
            case 1:
                return entity.getTenhk();
            case 2:
                return entity.getNamhoc();
            case 3:
                return entity.getNgaybatdau().toLocalDateTime().toString();
            case 4:
                return entity.getNgayketthuc().toLocalDateTime().toString();
            default:
                return null;
        }
    }
}
