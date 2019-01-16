package service;

import domain.Trade;
import domain.Trade.Instruction;

import java.util.List;

public interface EntityRankingService {

    List<Trade> rankEntitiesByInstructedAmount(Instruction instruction, List<Trade> trades);

}
