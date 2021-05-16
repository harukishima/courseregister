package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "kidangkihocphan", schema = "public", catalog = "coursedb")
public class KidangkihocphanEntity {
    private int idkidk;
    private String tenhk;
    private Integer namhoc;
    private Timestamp ngaybatdau;
    private Timestamp ngayketthuc;
    private HockiEntity hocki;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkidk")
    public int getIdkidk() {
        return idkidk;
    }

    public void setIdkidk(int idkidk) {
        this.idkidk = idkidk;
    }

    @Basic
    @Column(name = "tenhk")
    public String getTenhk() {
        return tenhk;
    }

    public void setTenhk(String tenhk) {
        this.tenhk = tenhk;
    }

    @Basic
    @Column(name = "namhoc")
    public Integer getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(Integer namhoc) {
        this.namhoc = namhoc;
    }

    @Basic
    @Column(name = "ngaybatdau")
    public Timestamp getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(Timestamp ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    @Basic
    @Column(name = "ngayketthuc")
    public Timestamp getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(Timestamp ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KidangkihocphanEntity that = (KidangkihocphanEntity) o;

        if (idkidk != that.idkidk) return false;
        if (tenhk != null ? !tenhk.equals(that.tenhk) : that.tenhk != null) return false;
        if (namhoc != null ? !namhoc.equals(that.namhoc) : that.namhoc != null) return false;
        if (ngaybatdau != null ? !ngaybatdau.equals(that.ngaybatdau) : that.ngaybatdau != null) return false;
        if (ngayketthuc != null ? !ngayketthuc.equals(that.ngayketthuc) : that.ngayketthuc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idkidk;
        result = 31 * result + (tenhk != null ? tenhk.hashCode() : 0);
        result = 31 * result + (namhoc != null ? namhoc.hashCode() : 0);
        result = 31 * result + (ngaybatdau != null ? ngaybatdau.hashCode() : 0);
        result = 31 * result + (ngayketthuc != null ? ngayketthuc.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "tenhk", referencedColumnName = "tenhk", insertable = false, updatable= false), @JoinColumn(name = "namhoc", referencedColumnName = "namhoc", insertable = false, updatable= false)})
    public HockiEntity getHocki() {
        return hocki;
    }

    public void setHocki(HockiEntity hocki) {
        this.hocki = hocki;
    }
}
