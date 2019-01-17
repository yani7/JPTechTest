package service.serviceImpl;

import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.LocalDate;
import service.ReportService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    @Override
    public void printDailySettledAmountReport(Instruction instruction, Map<LocalDate, BigDecimal> tradesPerDates) {
        System.out.println("SETTLED " + getReportType(instruction) + "VALUE DAILY REPORT"); ;
        for (Map.Entry<LocalDate, BigDecimal> tradesPerDate : tradesPerDates.entrySet()) {
            LocalDate key = tradesPerDate.getKey();
            System.out.println("Settlement Date: " + key);
            System.out.println("Total " + getReportTypeTitlecase(instruction) + " Amount: " + tradesPerDates.get(key).toString());
            System.out.println();
        }
    }

    @Override
    public void printRankingReport(Instruction instruction, List<Trade> trades) {
        System.out.println(getReportType(instruction) + "RANKING REPORT");
        for (int i = 0; i < trades.size(); i++){
            Trade trade = trades.get(i);
            System.out.print("Rank " + (i +1) + ": ");
            System.out.print(trade.getEntity().getEntityName() + " ");
            System.out.print(trade.getInstruction() + " ");
            System.out.println(trade.calculateValueOfTrade());
        }
        System.out.println();
    }

    private String getReportType(Instruction instruction) {
        if (instruction.equals(Instruction.Buy)) {
            return "OUTGOING ";
        } else {
            return "INCOMING ";
        }
    }

    private String getReportTypeTitlecase(Instruction instruction) {
        if (instruction.equals(Instruction.Buy)) {
            return "Outgoing ";
        } else {
            return "Incoming ";
        }
    }
}

