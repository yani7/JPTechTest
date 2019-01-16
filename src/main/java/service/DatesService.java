package service;

import domain.Trade;

public interface DatesService {

    Trade validateSettlementDate(Trade trade);

}
