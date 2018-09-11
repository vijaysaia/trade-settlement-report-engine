package com.jpmc.tradesettlement.report.service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import com.jpmc.tradesettlement.report.beans.Ticker;
import com.jpmc.tradesettlement.report.repository.UAEWorkingDays;
import com.jpmc.tradesettlement.report.repository.DefaultWorkingDays;
import com.jpmc.tradesettlement.report.repository.WorkingDays;

public class TickerSettlementDateCalc {
	
	/**
     * Helper function that calc settlement date for every given ticker
     * @param tickers the tickers of which the settlement date will be calcd
     */
    public static void calcSettlementDates(Set<Ticker> tickers) {
        tickers.forEach(TickerSettlementDateCalc::calcSettlementDate);
    }

    /**
     * calc the settlementDate Based on some logic
     * @param ticker the ticker of which the settlement date will be calcd
     */
    public static void calcSettlementDate(Ticker ticker) {
        // Select proper strategy based on the Currency
        final WorkingDays workingDaysMechanism = getWorkingDaysStrategy(ticker.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(ticker.getSettlementDate());

        if (newSettlementDate != null) {
            // set the correct settlement date
            ticker.setSettlementDate(newSettlementDate);
        }
    }

    /**
     * Select proper strategy based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static WorkingDays getWorkingDaysStrategy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return UAEWorkingDays.getInstance();
        }
        return DefaultWorkingDays.getInstance();
    }

}
