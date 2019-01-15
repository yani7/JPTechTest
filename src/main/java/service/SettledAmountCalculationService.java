package service;

import domain.Trade;
import domain.Trade.Instruction;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yanitsa on 8.1.2019 г..
 */
public interface SettledAmountCalculationService {

    Map<LocalDate, BigDecimal> calculateDailySettledAmountPerInstructionType(Instruction instruction, List<Trade> trades);

}
