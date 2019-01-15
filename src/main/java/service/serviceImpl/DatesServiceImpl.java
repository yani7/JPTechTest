package service.serviceImpl;

import domain.Trade;
import org.joda.time.LocalDate;
import service.DatesService;

import java.util.Set;

public class DatesServiceImpl implements DatesService {

    Set<String> sundayStartingCurrencies;

    public DatesServiceImpl(Set<String> sundayStartingCurrencies) {
        this.sundayStartingCurrencies = sundayStartingCurrencies;
    }

    @Override
    public Trade validateSettlementDate(Trade trade) {
        if(sundayStartingCurrencies.contains(trade.getFxRate().getCurrency())) {
            trade = validateSettlementDateForSundayStartingWeek(trade);
        } else {
            trade = validateSettlementDateForMondayStartingWeek(trade);
        }
        return trade;
    }

    private Trade validateSettlementDateForMondayStartingWeek(Trade trade) {
        LocalDate settlementDate = trade.getSystemDate().getSettlementDate();
        //Saturday = 6, Sunday = 7
        if(settlementDate.getDayOfWeek() == 6){
            trade.getSystemDate().setSettlementDate(settlementDate.plusDays(2));
        } else if(settlementDate.getDayOfWeek() == 7){
            trade.getSystemDate().setSettlementDate(settlementDate.plusDays(1));
        }
        return trade;
    }

    private Trade validateSettlementDateForSundayStartingWeek(Trade trade) {
        LocalDate settlementDate = trade.getSystemDate().getSettlementDate();
        //Friday = 5, Saturday = 6
        if( settlementDate.getDayOfWeek() == 5){
            trade.getSystemDate().setSettlementDate(settlementDate.plusDays(2));
        } else if( settlementDate.getDayOfWeek() == 6){
            trade.getSystemDate().setSettlementDate(settlementDate.plusDays(1));
        }
        return trade;
    }
}
