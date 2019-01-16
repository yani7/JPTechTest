package domain;

import org.joda.time.LocalDate;

public class SystemDate {

    private LocalDate instructionDate;

    private LocalDate settlementDate;

    public SystemDate(LocalDate instructionDate, LocalDate settlementDate) {
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) { this.settlementDate = settlementDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemDate that = (SystemDate) o;

        if (!instructionDate.equals(that.instructionDate)) return false;
        return settlementDate.equals(that.settlementDate);
    }

    @Override
    public int hashCode() {
        int result = instructionDate.hashCode();
        result = 31 * result + settlementDate.hashCode();
        return result;
    }
}
