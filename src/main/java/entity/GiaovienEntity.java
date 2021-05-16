package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "giaovien", schema = "public", catalog = "coursedb")
public class GiaovienEntity {
    private int magv;
    private String fullname;
    private Collection<HocphanEntity> hocphansByMagv;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magv")
    public int getMagv() {
        return magv;
    }

    public void setMagv(int magv) {
        this.magv = magv;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiaovienEntity that = (GiaovienEntity) o;

        if (magv != that.magv) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = magv;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "giaovienByMagvlt")
    public Collection<HocphanEntity> getHocphansByMagv() {
        return hocphansByMagv;
    }

    public void setHocphansByMagv(Collection<HocphanEntity> hocphansByMagv) {
        this.hocphansByMagv = hocphansByMagv;
    }
}
