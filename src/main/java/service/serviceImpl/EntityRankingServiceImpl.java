package service.serviceImpl;

import domain.Entity;
import domain.Trade;
import domain.Trade.Instruction;
import service.EntityRankingService;

import java.util.*;
import java.util.stream.Collectors;

public class EntityRankingServiceImpl implements EntityRankingService {

    @Override
    public List<Trade> rankEntitiesByInstructedAmount(Instruction instruction, List<Trade> trades) {
        trades = removeIrrelevantTradesPerInstructionType(instruction, trades);
        trades.sort(Comparator.comparing(Trade::calculateValueOfTrade).reversed());
        trades = removeTradesForEntitiesAlreadyInTheRanking(trades);
        return trades;
    }

    private List<Trade> removeIrrelevantTradesPerInstructionType(Instruction instruction, List<Trade> trades) {
        return trades.stream().filter(trade -> trade.getInstruction().equals(instruction))
                .collect(Collectors.toList());
    }

    private List<Trade> removeTradesForEntitiesAlreadyInTheRanking (List<Trade> trades) {
        Set<Entity> rankedEntities = new HashSet();
        List<Trade> tradesToRemove = new LinkedList<>();
        trades.forEach(trade -> {
            if(rankedEntities.contains(trade.getEntity())){
                tradesToRemove.add(trade);
            } else {
                rankedEntities.add(trade.getEntity());
            }
        });
        trades.removeAll(tradesToRemove);
        return trades;
    }
}
