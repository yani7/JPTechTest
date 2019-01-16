package domain;

import java.math.BigDecimal;

public class Entity {

    private String entityName;

    private BigDecimal pricePerUnit;

    public Entity(String entityName, BigDecimal pricePerUnit) {
        this.entityName = entityName;
        this.pricePerUnit = pricePerUnit;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (!entityName.equals(entity.entityName)) return false;
        return pricePerUnit.equals(entity.pricePerUnit);
    }

    @Override
    public int hashCode() {
        int result = entityName.hashCode();
        result = 31 * result + pricePerUnit.hashCode();
        return result;
    }
}
