package domain;

import java.math.BigDecimal;

/**
 * Created by Yanitsa on 8.1.2019 г..
 */
public class Price {

    private String entityName;

    private BigDecimal pricePerUnit;

    public Price(String entityName, BigDecimal pricePerUnit) {
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
}
