package com.jpmc.tradesettlement.report;

import java.util.Set;

import com.jpmc.tradesettlement.report.beans.Ticker;
import com.jpmc.tradesettlement.report.repository.TickersGenerator;
import com.jpmc.tradesettlement.report.service.ReportGenerator;
import com.jpmc.tradesettlement.report.service.ReportGeneratorImpl;

public class TradeSettlementReportApp {
	public static void main(String[] args) {
		final Set<Ticker> tickers = TickersGenerator.getTickers();
		final ReportGenerator reportGenerator = new ReportGeneratorImpl();
		System.out.println(reportGenerator.generateTickersReport(tickers));
	}
}
