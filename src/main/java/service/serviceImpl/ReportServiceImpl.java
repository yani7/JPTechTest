package service.serviceImpl;

import org.joda.time.LocalDate;
import service.ReportService;

import java.math.BigDecimal;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    public enum ReportType {
        Outgoing, Incoming
    }

    public ReportServiceImpl() {
    }

    @Override
    public void printReport(ReportType reportType, Map<LocalDate, BigDecimal> tradesPerDates) {
        if (reportType.equals(ReportType.Outgoing)) {
            System.out.println("OUTGOING DAILY REPORT");
        } else {
            System.out.println("INCOMING DAILY REPORT");
        }
        for (Map.Entry<LocalDate, BigDecimal> tradesPerDate : tradesPerDates.entrySet()) {
            LocalDate key = tradesPerDate.getKey();
            System.out.println("Settlement Date: " + key);
            System.out.println("Total Outgoing Amount: " + tradesPerDates.get(key).toString());
            System.out.println();
        }
        ;
    }
}

