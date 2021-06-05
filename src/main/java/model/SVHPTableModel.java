package model;

import entity.SvdkhpEntity;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class SVHPTableModel extends AbstractTableModel {
    private List<SvdkhpEntity> list;
    private String[] columnNames = {"MSSV", "Họ tên", "Mã môn học", "Tên môn học", "Giáo viên LT", "Thời gian học",
            "Thời gian đăng kí"};

    public SVHPTableModel(List<SvdkhpEntity> list) {
        this.list = list;
    }

    public List<SvdkhpEntity> getList() {
        return list;
    }

    public void setList(List<SvdkhpEntity> list) {
        this.list = list;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: case 2:
                return Integer.class;
            case 1: case 3: case 4:
                return String.class;
            case 5:
                return Date.class;
            case 6:
                return Timestamp.class;
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
        SvdkhpEntity entity = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entity.getMasv();
            case 1:
                return entity.getSinhvienByMasv().getFullname();
            case 2:
                return entity.getHocphanByIdhocphan().getMamh();
            case 3:
                return entity.getHocphanByIdhocphan().getMonhocByMamh().getTenmh();
            case 4:
                return entity.getHocphanByIdhocphan().getGiaovienByMagvlt().getFullname();
            case 5:
                return entity.getHocphanByIdhocphan().getNgaybatdau();
            case 6:
                return entity.getThoigiandk();
            default:
                return null;
        }
    }
}
