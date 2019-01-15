package service.serviceImpl;

import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import service.DatesService;
import service.SettledAmountCalculationService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SettledAmountCalcServiceImpl implements SettledAmountCalculationService {

    private DatesService datesService;

    public SettledAmountCalcServiceImpl(DatesService datesService) {
        this.datesService = datesService;
    }

    public Map<LocalDate, BigDecimal> calculateDailySettledAmountPerInstructionType(Instruction instruction, List<Trade> trades) {
        List<Trade> outgoingTrades = removeIrrellevantTradesPerInstructionType(instruction, trades);
        outgoingTrades.forEach(trade -> {
            trade = datesService.validateSettlementDate(trade);
        });

        Set<LocalDate> distinctDates = new HashSet<>();
        outgoingTrades.forEach(trade -> {
            distinctDates.add(trade.getSystemDate().getSettlementDate());
        });

        Map<LocalDate, BigDecimal> outgoingAmountMap = new HashMap<LocalDate, BigDecimal>();
        distinctDates.forEach(date -> {
            BigDecimal outgoingAmountTotal = new BigDecimal("0.0");
            for (Trade trade : outgoingTrades) {
                if (trade.getSystemDate().getSettlementDate().equals(date)) {
                    BigDecimal tradeAmount = calculateValueOfTrade(trade);
                    outgoingAmountTotal = outgoingAmountTotal.add(tradeAmount);
                }
            }
            outgoingAmountMap.putIfAbsent(date, outgoingAmountTotal);
        });
        return outgoingAmountMap;
    }

    private List<Trade> removeIrrellevantTradesPerInstructionType(Instruction instruction, List<Trade> trades) {
        return trades.stream().filter(trade -> trade.getInstruction().equals(instruction))
                .collect(Collectors.toList());
    }

    private BigDecimal calculateValueOfTrade(Trade trade) {
        return trade.getPrice().getPricePerUnit()
                .multiply(new BigDecimal(trade.getUnitNumber()))
                    .multiply(trade.getFxRate().getAgreedRate());
    }
}
