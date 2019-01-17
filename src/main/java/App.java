import domain.FxRate;
import domain.Entity;
import domain.SystemDate;
import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.LocalDate;
import service.DatesService;
import service.EntityRankingService;
import service.ReportService;
import service.DailySettledAmountService;
import service.serviceImpl.DatesServiceImpl;
import service.serviceImpl.EntityRankingServiceImpl;
import service.serviceImpl.ReportServiceImpl;
import service.serviceImpl.DailySettledAmountServiceImpl;

import java.math.BigDecimal;
import java.util.*;

public class App {
    public static void main(String[] args){
        //Daily Settled Amount reporting
        Set<String> sundayStartingCurrencies = new HashSet<>();
        sundayStartingCurrencies.add("AED");
        sundayStartingCurrencies.add("SAR");

        DatesService datesService = new DatesServiceImpl(sundayStartingCurrencies);

        DailySettledAmountService dailySettledAmountService = new DailySettledAmountServiceImpl(datesService);
        ReportService reportService = new ReportServiceImpl();

        FxRate fxRateSGP = new FxRate("SGP",new BigDecimal("0.50"));
        FxRate fxRateAED = new FxRate("AED",new BigDecimal("0.22"));

        Entity entityFoo = new Entity("Foo", new BigDecimal("100.25"));
        Entity entityBar = new Entity("Bar", new BigDecimal("150.5"));
        Entity entityFizz = new Entity("Fizz", new BigDecimal("135.70"));
        Entity entityBuzz = new Entity("Buzz", new BigDecimal("180.55"));

        SystemDate systemDateOne = new SystemDate(new LocalDate(2016, 01, 01), new LocalDate(2016, 01, 04));
        SystemDate systemDateTwo = new SystemDate(new LocalDate(2016, 01, 8), new LocalDate(2016, 01, 9));
        SystemDate systemDateThree = new SystemDate(new LocalDate(2016, 01, 7), new LocalDate(2016, 01, 8));
        SystemDate systemDateFour = new SystemDate(new LocalDate(2016, 01, 14), new LocalDate(2016, 01, 16));

        Trade tradeOne = new Trade(1l, Instruction.Buy, systemDateOne, fxRateSGP, entityFoo, 200);
        Trade tradeTwo = new Trade(2l, Instruction.Sell, systemDateTwo, fxRateSGP, entityFoo, 450);
        Trade tradeThree = new Trade(3l, Instruction.Buy, systemDateThree, fxRateAED, entityBar, 250);
        Trade tradeFour = new Trade(4l, Instruction.Sell, systemDateFour, fxRateAED, entityBar, 150);
        Trade tradeFive = new Trade(5l, Instruction.Buy, systemDateTwo, fxRateSGP, entityFizz, 300);
        Trade tradeSix = new Trade(6l, Instruction.Sell, systemDateFour, fxRateAED, entityBuzz, 410);

        List<Trade> trades = new LinkedList<>();
        trades.add(tradeOne);
        trades.add(tradeTwo);
        trades.add(tradeThree);
        trades.add(tradeFour);
        trades.add(tradeFive);
        trades.add(tradeSix);

        Map<LocalDate, BigDecimal> report;
        report = dailySettledAmountService
                .calculateDailySettledAmountPerInstructionType(Instruction.Buy, trades);

        reportService.printDailySettledAmountReport(Instruction.Buy, report);

        report = dailySettledAmountService
                .calculateDailySettledAmountPerInstructionType(Instruction.Sell, trades);

        reportService.printDailySettledAmountReport(Instruction.Sell, report);

        //Ranking reporting
        EntityRankingService entityRankingService = new EntityRankingServiceImpl();

        //Adding trades with duplicate entities to test removal
        Trade tradeSeven = new Trade(7l, Instruction.Buy, systemDateTwo, fxRateSGP, entityFizz, 300);
        Trade tradeEight = new Trade(8l, Instruction.Sell, systemDateFour, fxRateAED, entityBuzz, 410);

        trades.add(tradeSeven);
        trades.add(tradeEight);

        List<Trade> rankedOutgoingTrades = entityRankingService.rankEntitiesByInstructedAmount(Instruction.Buy, trades);
        reportService.printRankingReport(Instruction.Buy, rankedOutgoingTrades);

        List<Trade> rankedIncomingTrades = entityRankingService.rankEntitiesByInstructedAmount(Instruction.Sell, trades);
        reportService.printRankingReport(Instruction.Sell, rankedIncomingTrades);
    }
}
