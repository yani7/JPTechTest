import domain.FxRate;
import domain.Price;
import domain.SystemDate;
import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import service.DatesService;
import service.ReportService;
import service.SettledAmountCalculationService;
import service.serviceImpl.DatesServiceImpl;
import service.serviceImpl.ReportServiceImpl;
import service.serviceImpl.ReportServiceImpl.ReportType;
import service.serviceImpl.SettledAmountCalcServiceImpl;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Yanitsa on 8.1.2019 г..
 */
public class App {
    public static void main(String[] args){
        Set<String> sundayStartingCurrencies = new HashSet<>();
        sundayStartingCurrencies.add("AED");
        sundayStartingCurrencies.add("SAR");

        DatesService datesService = new DatesServiceImpl(sundayStartingCurrencies);

        SettledAmountCalculationService settledAmountCalculationService = new SettledAmountCalcServiceImpl(datesService);
        ReportService reportService = new ReportServiceImpl();

        FxRate fxRateSGP = new FxRate("SGP",new BigDecimal("0.50"));
        FxRate fxRateAED = new FxRate("AED",new BigDecimal("0.22"));

        Price priceFoo = new Price("Foo", new BigDecimal("100.25"));
        Price priceBar = new Price("Bar", new BigDecimal("150.5"));

        SystemDate systemDateOne = new SystemDate(new LocalDate(2016, 01, 01), new LocalDate(2016, 01, 04));
        SystemDate systemDateTwo = new SystemDate(new LocalDate(2016, 01, 8), new LocalDate(2016, 01, 9));
        SystemDate systemDateThree = new SystemDate(new LocalDate(2016, 01, 7), new LocalDate(2016, 01, 8));
        SystemDate systemDateFour = new SystemDate(new LocalDate(2016, 01, 14), new LocalDate(2016, 01, 16));

        Trade tradeOne = new Trade(1l, Instruction.Buy, systemDateOne, fxRateSGP, priceFoo, 200);
        Trade tradeTwo = new Trade(2l, Instruction.Sell, systemDateTwo, fxRateSGP, priceBar, 450);
        Trade tradeThree = new Trade(3l, Instruction.Buy, systemDateThree, fxRateAED, priceBar, 250);
        Trade tradeFour = new Trade(4l, Instruction.Sell, systemDateFour, fxRateAED, priceBar, 150);

        List<Trade> trades = new LinkedList<>();
        trades.add(tradeOne);
        trades.add(tradeTwo);
        trades.add(tradeThree);
        trades.add(tradeFour);

        Map<LocalDate, BigDecimal> report = new HashMap<>();
        report = settledAmountCalculationService
                .calculateDailySettledAmountPerInstructionType(Instruction.Buy, trades);

        reportService.printReport(ReportType.Outgoing, report);

        report = settledAmountCalculationService
                .calculateDailySettledAmountPerInstructionType(Instruction.Sell, trades);

        reportService.printReport(ReportType.Incoming, report);
    }
}
