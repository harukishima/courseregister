package view;

import dao.SinhvienDAO;
import dao.SvdkhpDAO;
import dao.dataCRUD;
import entity.SinhvienEntity;
import entity.SvdkhpEntity;
import model.SVHPTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HPSinhVienForm extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTable table;
    private JPanel panel;
    private int idhocphan;
    private SVHPTableModel svhpTableModel;

    public HPSinhVienForm(int idhocphan) throws HeadlessException {
        this.idhocphan = idhocphan;
        initForm();
    }

    public void initForm() {
        add(panel);
        setTitle("Danh sách sinh viên");
        setSize(800,600);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(searchButton);
        setVisible(true);

        List<SvdkhpEntity> list = SvdkhpDAO.getListSVOfHP(idhocphan);
        if (list == null) {
            (new ErrorDialog("Không thể lấy danh sách")).setVisible(true);
            dispose();
        }
        svhpTableModel = new SVHPTableModel(list);
        table.setModel(svhpTableModel);
        searchButton.addActionListener(e -> searchSV());
    }

    public void updateTable(List<SvdkhpEntity> list) {
        svhpTableModel.setList(list);
    }

    public void updateTableList() {
        List<SvdkhpEntity> list = SvdkhpDAO.getListSVOfHP(idhocphan);
        updateTable(list);
    }

    private void searchSV() {
        List<SvdkhpEntity> list;
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            updateTableList();
            return;
        }
        try {
            int ms = Integer.parseInt(searchText);
            list = new ArrayList<>();
            SvdkhpEntity entity = SvdkhpDAO.getByIdSVandHP(ms, idhocphan);
            if (entity != null) {
                list.add(entity);
            }
        } catch (NumberFormatException ex) {
            list = SvdkhpDAO.findSV(idhocphan, searchText);
        }
        updateTable(list);
    }
}
