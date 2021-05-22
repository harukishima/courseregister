package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "lop", schema = "public", catalog = "coursedb")
public class LopEntity {
    private int malop;
    private Collection<SinhvienEntity> sinhviensByMalop;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "malop")
    public int getMalop() {
        return malop;
    }

    public void setMalop(int malop) {
        this.malop = malop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LopEntity lopEntity = (LopEntity) o;

        if (malop != lopEntity.malop) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return malop;
    }

    @OneToMany(mappedBy = "lopByMalop")
    public Collection<SinhvienEntity> getSinhviensByMalop() {
        return sinhviensByMalop;
    }

    public void setSinhviensByMalop(Collection<SinhvienEntity> sinhviensByMalop) {
        this.sinhviensByMalop = sinhviensByMalop;
    }

    @Override
    public String toString() {
        return "LopEntity{" +
                "malop=" + malop +
                ", sinhviensByMalop=" + sinhviensByMalop +
                '}';
    }
}
