package com.jpmc.tradesettlement.report.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.jpmc.tradesettlement.report.beans.Ticker;
import com.jpmc.tradesettlement.report.beans.TickerDetails;
import com.jpmc.tradesettlement.report.beans.TradeAction;

public class TickersGenerator {

	public static Set<Ticker> getTickers() {

		return new HashSet<Ticker>(Arrays.asList(

				new Ticker("infy", TradeAction.BUY, LocalDate.of(2018, 9, 1), LocalDate.of(2018, 9, 2),
						new TickerDetails(Currency.getInstance("SGD"), BigDecimal.valueOf(0.40), 200,
								BigDecimal.valueOf(99.25))),

				new Ticker("icici", TradeAction.BUY, LocalDate.of(2018, 9, 1), LocalDate.of(2018, 9, 2),
						new TickerDetails(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450,
								BigDecimal.valueOf(140.5))),

				new Ticker("amex", TradeAction.SELL, LocalDate.of(2018, 9, 3), LocalDate.of(2018, 9, 4),
						new TickerDetails(Currency.getInstance("SAR"), BigDecimal.valueOf(0.17), 150,
								BigDecimal.valueOf(300.8))),

				new Ticker("citi", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new TickerDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 50,
								BigDecimal.valueOf(500.6))),

				new Ticker("tata", TradeAction.BUY, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new TickerDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.24), 20,
								BigDecimal.valueOf(30.6))),

				new Ticker("hcl", TradeAction.BUY, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new TickerDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.24), 20,
								BigDecimal.valueOf(30.6))),

				new Ticker("snp", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new TickerDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.31), 1000,
								BigDecimal.valueOf(150.6))),

				new Ticker("boa", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new TickerDetails(Currency.getInstance("USD"), BigDecimal.valueOf(0.30), 120,
								BigDecimal.valueOf(400.6)))));
	}

}
