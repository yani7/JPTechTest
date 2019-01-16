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

        if (instructionDate != null ? !instructionDate.equals(that.instructionDate) : that.instructionDate != null)
            return false;
        return settlementDate != null ? settlementDate.equals(that.settlementDate) : that.settlementDate == null;
    }

    @Override
    public int hashCode() {
        int result = instructionDate != null ? instructionDate.hashCode() : 0;
        result = 31 * result + (settlementDate != null ? settlementDate.hashCode() : 0);
        return result;
    }
}
