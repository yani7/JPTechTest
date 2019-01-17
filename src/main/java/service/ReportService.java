package service;

import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportService {

    void printDailySettledAmountReport(Instruction instruction, Map<LocalDate, BigDecimal> tradesPerDate);

    void printRankingReport(Instruction instruction, List<Trade> trades);

}
