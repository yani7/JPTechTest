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
}
