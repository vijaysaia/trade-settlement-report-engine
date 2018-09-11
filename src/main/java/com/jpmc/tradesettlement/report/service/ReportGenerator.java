package com.jpmc.tradesettlement.report.service;

import java.util.Set;

import com.jpmc.tradesettlement.report.beans.Ticker;

public interface ReportGenerator {
	
	/**
	 * Generates Daily Trade Settle reports for the given  set of tickers
	 * @param tickers
	 * @return String
	 */
	String generateTickersReport(Set<Ticker> tickers);

}
