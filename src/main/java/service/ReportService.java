package service;

import domain.Trade;
import org.joda.time.LocalDate;
import service.serviceImpl.ReportServiceImpl;
import service.serviceImpl.ReportServiceImpl.ReportType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yanitsa on 9.1.2019 г..
 */
public interface ReportService {

    void printDailySettledAmountReport(ReportType reportType, Map<LocalDate, BigDecimal> tradesPerDate);

    void printRankingReport(ReportType reportType, List<Trade> trades);

}
