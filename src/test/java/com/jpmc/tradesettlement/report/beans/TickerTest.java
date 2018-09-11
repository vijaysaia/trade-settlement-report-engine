package com.jpmc.tradesettlement.report.beans;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.jpmc.tradesettlement.report.beans.Ticker;
import com.jpmc.tradesettlement.report.beans.TickerDetails;
import com.jpmc.tradesettlement.report.beans.TradeAction;

public class TickerTest {

	@Test
	public void testTradeAmountCalc() throws Exception {
		final BigDecimal agreedFx = BigDecimal.valueOf(0.60);
		final BigDecimal pricePerUnit = BigDecimal.valueOf(99.25);
		final int units = 100;

		final Ticker ticker = new Ticker("infy", TradeAction.BUY, LocalDate.of(2018, 9, 9), LocalDate.of(2018, 9, 10),
				new TickerDetails(Currency.getInstance("SGD"), agreedFx, units, pricePerUnit));
		assertEquals(agreedFx, ticker.getAgreedFx());
		assertEquals(pricePerUnit, ticker.getPricePerUnit());
		assertEquals(units, ticker.getUnits());

		final BigDecimal correct = pricePerUnit.multiply(agreedFx).multiply(BigDecimal.valueOf(units)).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);
		assertEquals(correct, ticker.getTradeAmount());
	}

}
