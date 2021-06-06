package model;

import dao.HockiDAO;
import dao.HocphanDAO;
import dao.SvdkhpDAO;
import dao.dataCRUD;
import entity.*;
import org.postgresql.util.PGTimestamp;
import view.DKHPForm;
import view.ErrorDialog;
import view.SuccessDialog;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class DKHPController {
    private List<HocphanEntity> listHP;
    private List<Integer> tempDK = new ArrayList<>();
    private final HockiEntity hk;
    private final SinhvienEntity sv;
    private List<SvdkhpEntity> dangKi;
    private Map<Integer,int[]> slotByHP = new HashMap<>();
    private JDialog dialog;
    private DKHPForm dkhpForm;

    public DKHPController(HockiEntity hk, SinhvienEntity sv, DKHPForm dkhpForm) {
        this.dkhpForm = dkhpForm;
        this.hk = hk;
        this.sv = sv;
        initHP();
        loadDK();
        initSlot();
    }

    public void initHP() {
        listHP = HocphanDAO.getListLoadAllByHocKy(hk);
    }

    public void initSlot() {
        slotByHP.clear();
        for (HocphanEntity entity : listHP) {
            int totalSlot = entity.getSlot();
            int slot = HocphanDAO.countDK(entity.getIdhocphan());
            slotByHP.put(entity.getIdhocphan(), new int[]{slot, totalSlot});
        }
    }

    public void loadDK() {
        dangKi = SvdkhpDAO.findDKBySVandHK(sv.getMasv(), hk);
        tempDK.clear();
        for(SvdkhpEntity entity : dangKi) {
            tempDK.add(entity.getIdhocphan());
        }
    }

    public void addToDk(HocphanEntity hp) {
        int[] slot = slotByHP.get(hp.getIdhocphan());
        if (!tempDK.contains(hp.getIdhocphan()) && tempDK.size() < 8 && checkTrungCa(hp) && slot[0] < slot[1]) {
            tempDK.add(hp.getIdhocphan());
        }
    }

    public void removeFromDk(int mahp) {
        if (tempDK.contains(mahp)) {
            tempDK.remove(Integer.valueOf(mahp));
        }
    }

    public boolean isInDk(int mahp) {
        return tempDK.contains(mahp);
    }

    public HocphanEntity getHP(int index) {
        return listHP.get(index);
    }

    public int getListHPSize() {
        return listHP.size();
    }

    public String getSlotOfHP(int id) {
        return slotByHP.get(id)[0] + "/" + slotByHP.get(id)[1];
    }

    public List<HocphanEntity> IdtoHPEntity(List<Integer> id) {
        List<HocphanEntity> hocphanEntities = new ArrayList<>();
        for (HocphanEntity entity : listHP) {
            if (id.contains(entity.getIdhocphan())) {
                hocphanEntities.add(entity);
            }
        }
        return hocphanEntities;
    }

    public boolean checkTrungCa(HocphanEntity hp) {
        List<HocphanEntity> list = IdtoHPEntity(tempDK);
        for (HocphanEntity entity : list) {
            if (entity.getCa().equals(hp.getCa()) &&
                    entity.getNgaytrongtuan().equals(hp.getNgaytrongtuan())) {
                return false;
            }
        }
        return true;
    }

//    public boolean checkTrongKiDK() {
//        List<KidangkihocphanEntity> kidangkihocphanEntityList = (List<KidangkihocphanEntity>) hk.getKidangkihocphans();
//        LocalDateTime cur = LocalDateTime.now();
//        for(KidangkihocphanEntity entity : kidangkihocphanEntityList) {
//            LocalDateTime start = entity.getNgaybatdau().toLocalDateTime();
//            LocalDateTime end = entity.getNgayketthuc().toLocalDateTime();
//            if (start.isBefore(cur) && end.isAfter(cur)) {
//                return true;
//            }
//        }
//        return false;
//    }

    private boolean checkInDangKi(int idhocphan) {
        for (SvdkhpEntity entity : dangKi) {
            if (entity.getIdhocphan() == idhocphan) {
                return true;
            }
        }
        return false;
    }

    public void submit() {
        if (!HockiDAO.checkTrongKiDK()) {
            dialog = new ErrorDialog("Đã quá giờ đăng kí");
            dialog.setVisible(true);
            return;
        }
        for (SvdkhpEntity entity : dangKi) {
            if (!tempDK.contains(entity.getIdhocphan())) {
                dataCRUD.deleteEntity(entity);
            }
        }
        List<String> unavailable = new ArrayList<>();
        for (HocphanEntity entity : listHP) {
            if (!checkInDangKi(entity.getIdhocphan()) && tempDK.contains(entity.getIdhocphan())) {
                int totalSlot = slotByHP.get(entity.getIdhocphan())[1];
                int slot = HocphanDAO.countDK(entity.getIdhocphan());
                if (slot >= totalSlot) {
                    unavailable.add(entity.getMonhocByMamh().getTenmh());
                    continue;
                }
                SvdkhpEntity svdkhpEntity = new SvdkhpEntity();
                svdkhpEntity.setMasv(sv.getMasv());
                svdkhpEntity.setIdhocphan(entity.getIdhocphan());
                PGTimestamp pgTimestamp = new PGTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(7))*1000L);
                svdkhpEntity.setThoigiandk(pgTimestamp);
                dataCRUD.insertEntity(svdkhpEntity);
            }
        }
        if (!unavailable.isEmpty()) {
            String detail = String.join(", ", unavailable);
            dialog = new ErrorDialog("Không thể đăng kí các học phần sau: \n" + detail);
            dialog.setVisible(true);
        }
        dialog = new SuccessDialog("Đã đăng kí hoàn tất");
        dialog.setVisible(true);
        dkhpForm.dispatchEvent(new WindowEvent(dkhpForm, WindowEvent.WINDOW_CLOSING));
    }


}
