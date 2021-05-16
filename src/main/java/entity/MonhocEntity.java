package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "monhoc", schema = "public", catalog = "coursedb")
public class MonhocEntity {
    private int mamh;
    private String tenmh;
    private Short sotinchi;
    private Collection<HocphanEntity> hocphansByMamh;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mamh")
    public int getMamh() {
        return mamh;
    }

    public void setMamh(int mamh) {
        this.mamh = mamh;
    }

    @Basic
    @Column(name = "tenmh")
    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    @Basic
    @Column(name = "sotinchi")
    public Short getSotinchi() {
        return sotinchi;
    }

    public void setSotinchi(Short sotinchi) {
        this.sotinchi = sotinchi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonhocEntity that = (MonhocEntity) o;

        if (mamh != that.mamh) return false;
        if (tenmh != null ? !tenmh.equals(that.tenmh) : that.tenmh != null) return false;
        if (sotinchi != null ? !sotinchi.equals(that.sotinchi) : that.sotinchi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mamh;
        result = 31 * result + (tenmh != null ? tenmh.hashCode() : 0);
        result = 31 * result + (sotinchi != null ? sotinchi.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "monhocByMamh")
    public Collection<HocphanEntity> getHocphansByMamh() {
        return hocphansByMamh;
    }

    public void setHocphansByMamh(Collection<HocphanEntity> hocphansByMamh) {
        this.hocphansByMamh = hocphansByMamh;
    }
}
