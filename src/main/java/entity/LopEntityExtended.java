package entity;

public class LopEntityExtended {
    private LopEntity entity;
    private int males;
    private int females;

    public LopEntityExtended() {
    }

    public LopEntityExtended(LopEntity entity, int males, int females) {
        this.entity = entity;
        this.males = males;
        this.females = females;
    }

    public LopEntity getEntity() {
        return entity;
    }

    public int getMales() {
        return males;
    }

    public int getFemales() {
        return females;
    }
}