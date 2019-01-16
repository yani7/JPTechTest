package service.serviceImpl;

import domain.Trade;
import org.joda.time.LocalDate;
import service.ReportService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    public enum ReportType {
        Outgoing, Incoming
    }

    @Override
    public void printDailySettledAmountReport(ReportType reportType, Map<LocalDate, BigDecimal> tradesPerDates) {
        System.out.println("SETTLED " + getReportType(reportType) + "VALUE DAILY REPORT"); ;
        for (Map.Entry<LocalDate, BigDecimal> tradesPerDate : tradesPerDates.entrySet()) {
            LocalDate key = tradesPerDate.getKey();
            System.out.println("Settlement Date: " + key);
            System.out.println("Total Outgoing Amount: " + tradesPerDates.get(key).toString());
            System.out.println();
        }
    }

    @Override
    public void printRankingReport(ReportType reportType, List<Trade> trades) {
        System.out.println(getReportType(reportType) + "RANKING REPORT");
        for (int i = 0; i < trades.size(); i++){
            Trade trade = trades.get(i);
            System.out.print("Rank " + (i +1) + ": ");
            System.out.print(trade.getEntity().getEntityName() + " ");
            System.out.print(trade.getInstruction() + " ");
            System.out.println(trade.calculateValueOfTrade());
        }
        System.out.println();
    }

    private String getReportType(ReportType reportType) {
        if (reportType.equals(ReportType.Outgoing)) {
            return "OUTGOING ";
        } else {
            return "INCOMING ";
        }
    }
}

