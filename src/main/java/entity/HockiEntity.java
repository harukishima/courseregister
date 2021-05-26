package entity;

import org.postgresql.util.PGTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "hocki", schema = "public", catalog = "coursedb")
@IdClass(HockiEntityPK.class)
public class HockiEntity {
    private String tenhk;
    private int namhoc;
    private Timestamp ngaybatdau;
    private Timestamp ngayketthuc;
    private Boolean hkhientai;
    private Collection<HocphanEntity> hocphans;
    private Collection<KidangkihocphanEntity> kidangkihocphans;

    @Id
    @Column(name = "tenhk")
    public String getTenhk() {
        return tenhk;
    }

    public void setTenhk(String tenhk) {
        this.tenhk = tenhk;
    }

    @Id
    @Column(name = "namhoc")
    public int getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(int namhoc) {
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

    @Basic
    @Column(name = "hkhientai")
    public Boolean getHkhientai() {
        return hkhientai;
    }

    public void setHkhientai(Boolean hkhientai) {
        this.hkhientai = hkhientai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HockiEntity that = (HockiEntity) o;

        if (namhoc != that.namhoc) return false;
        if (tenhk != null ? !tenhk.equals(that.tenhk) : that.tenhk != null) return false;
        if (ngaybatdau != null ? !ngaybatdau.equals(that.ngaybatdau) : that.ngaybatdau != null) return false;
        if (ngayketthuc != null ? !ngayketthuc.equals(that.ngayketthuc) : that.ngayketthuc != null) return false;
        if (hkhientai != null ? !hkhientai.equals(that.hkhientai) : that.hkhientai != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tenhk != null ? tenhk.hashCode() : 0;
        result = 31 * result + namhoc;
        result = 31 * result + (ngaybatdau != null ? ngaybatdau.hashCode() : 0);
        result = 31 * result + (ngayketthuc != null ? ngayketthuc.hashCode() : 0);
        result = 31 * result + (hkhientai != null ? hkhientai.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "hocki")
    public Collection<HocphanEntity> getHocphans() {
        return hocphans;
    }

    public void setHocphans(Collection<HocphanEntity> hocphans) {
        this.hocphans = hocphans;
    }

    @OneToMany(mappedBy = "hocki")
    public Collection<KidangkihocphanEntity> getKidangkihocphans() {
        return kidangkihocphans;
    }

    public void setKidangkihocphans(Collection<KidangkihocphanEntity> kidangkihocphans) {
        this.kidangkihocphans = kidangkihocphans;
    }
}
