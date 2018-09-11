package com.jpmc.tradesettlement.report.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.jpmc.tradesettlement.report.beans.Ticker;
import com.jpmc.tradesettlement.report.beans.TickerDetails;
import com.jpmc.tradesettlement.report.beans.TradeAction;
import com.jpmc.tradesettlement.report.service.TickerSettlementDateCalc;

import static org.junit.Assert.assertEquals;

public class TickerSettlementDateCalcTest {

	
	@Test
	public void calcSettlementDate_default_Sunday() throws Exception {
		final LocalDate initialSettlementDate_sunday = LocalDate.of(2018, 9, 2); 

		final Ticker ticker = new Ticker("infy", TradeAction.BUY, LocalDate.of(2018, 9, 2),
				initialSettlementDate_sunday, new TickerDetails(Currency.getInstance("SGD"), BigDecimal.valueOf(1), 200,
						BigDecimal.valueOf(100.25)));

		/* calc new settlement day */
		TickerSettlementDateCalc.calcSettlementDate(ticker);

		/* should be the first monday (3/9/2018) */
		assertEquals(LocalDate.of(2018, 9, 3), ticker.getSettlementDate());
	}

	

	@Test
	public void calcSettlementDate_UAE_Sunday() throws Exception {
		final LocalDate initialSettlementDate_sunday = LocalDate.of(2018, 9, 2); 

		final Ticker ticker = new Ticker("infy", TradeAction.BUY, LocalDate.of(2018, 9, 2),
				initialSettlementDate_sunday, new TickerDetails(Currency.getInstance("SAR"),
						BigDecimal.valueOf(0.50), 200, BigDecimal.valueOf(100.25)));

		// calc new settlement day
		TickerSettlementDateCalc.calcSettlementDate(ticker);

		// should be the same
		assertEquals(initialSettlementDate_sunday, ticker.getSettlementDate());
	}

}
