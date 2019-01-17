package service.serviceImpl;

import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.LocalDate;
import service.DatesService;
import service.DailySettledAmountService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DailySettledAmountServiceImpl implements DailySettledAmountService {

    private DatesService datesService;

    public DailySettledAmountServiceImpl(DatesService datesService) {
        this.datesService = datesService;
    }

    public Map<LocalDate, BigDecimal> calculateDailySettledAmountPerInstructionType(Instruction instruction, List<Trade> trades) {
        List<Trade> tradeList = removeIrrelevantTradesPerInstructionType(instruction, trades);
        tradeList.forEach(trade -> {
            trade = datesService.validateSettlementDate(trade);
        });

        Set<LocalDate> distinctDates = new HashSet<>();
        tradeList.forEach(trade -> {
            distinctDates.add(trade.getSystemDate().getSettlementDate());
        });

        Map<LocalDate, BigDecimal> outgoingAmountMap = new HashMap<LocalDate, BigDecimal>();
        distinctDates.forEach(date -> {
            BigDecimal totalAmount = new BigDecimal("0.0");
            for (Trade trade : tradeList) {
                if (trade.getSystemDate().getSettlementDate().equals(date)) {
                    BigDecimal tradeAmount = trade.calculateValueOfTrade();
                    totalAmount = totalAmount.add(tradeAmount);
                }
            }
            outgoingAmountMap.putIfAbsent(date, totalAmount);
        });
        return outgoingAmountMap;
    }

    private List<Trade> removeIrrelevantTradesPerInstructionType(Instruction instruction, List<Trade> trades) {
        return trades.stream().filter(trade -> trade.getInstruction().equals(instruction))
                .collect(Collectors.toList());
    }
}
