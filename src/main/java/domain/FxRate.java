package domain;

import java.math.BigDecimal;

public class FxRate {

    private String currency;

    private BigDecimal agreedRate;

    public FxRate(String currency, BigDecimal agreedRate) {
        this.currency = currency;
        this.agreedRate = agreedRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAgreedRate() {
        return agreedRate;
    }

    public void setAgreedRate(BigDecimal agreedRate) {
        this.agreedRate = agreedRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FxRate fxRate = (FxRate) o;

        if (!currency.equals(fxRate.currency)) return false;
        return agreedRate.equals(fxRate.agreedRate);
    }

    @Override
    public int hashCode() {
        int result = currency.hashCode();
        result = 31 * result + agreedRate.hashCode();
        return result;
    }
}
