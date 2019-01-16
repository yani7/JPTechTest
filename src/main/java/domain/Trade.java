package domain;

import java.math.BigDecimal;

public class Trade {

    public enum Instruction {
        Buy, Sell
    }

    private Long id;

    private Instruction instruction;

    private SystemDate systemDate;

    private FxRate fxRate;

    private Entity entity;

    private int unitNumber;

    public Trade(Long id, Instruction instruction, SystemDate systemDate, FxRate fxRate, Entity entity, int unitNumber) {
        this.id = id;
        this.instruction = instruction;
        this.systemDate = systemDate;
        this.fxRate = fxRate;
        this.entity = entity;
        this.unitNumber = unitNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public SystemDate getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(SystemDate systemDate) {
        this.systemDate = systemDate;
    }

    public FxRate getFxRate() {
        return fxRate;
    }

    public void setFxRate(FxRate fxRate) {
        this.fxRate = fxRate;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public BigDecimal calculateValueOfTrade() {
        return this.getEntity().getPricePerUnit()
                .multiply(new BigDecimal(this.getUnitNumber()))
                .multiply(this.getFxRate().getAgreedRate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (unitNumber != trade.unitNumber) return false;
        if (id != null ? !id.equals(trade.id) : trade.id != null) return false;
        if (instruction != trade.instruction) return false;
        if (systemDate != null ? !systemDate.equals(trade.systemDate) : trade.systemDate != null) return false;
        if (fxRate != null ? !fxRate.equals(trade.fxRate) : trade.fxRate != null) return false;
        return entity != null ? entity.equals(trade.entity) : trade.entity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (instruction != null ? instruction.hashCode() : 0);
        result = 31 * result + (systemDate != null ? systemDate.hashCode() : 0);
        result = 31 * result + (fxRate != null ? fxRate.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + unitNumber;
        return result;
    }
}
