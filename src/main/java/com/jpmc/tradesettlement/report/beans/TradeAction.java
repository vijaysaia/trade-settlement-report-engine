package com.jpmc.tradesettlement.report.beans;

import com.jpmc.tradesettlement.report.exceptions.TradeSettlementReportException;

public enum TradeAction {

	BUY("B"), SELL("S");

	private String value;

	TradeAction(String value) {
		this.value = value;
	}

	public String getText() {
		return this.value;
	}

	public static TradeAction fromString(String val) {

		if (val != null) {
			for (TradeAction tmp : TradeAction.values()) {
				if (val.equalsIgnoreCase(tmp.value)) {
					return tmp;
				}
			}
			throw new TradeSettlementReportException("invalid input. allowed values (B | S)");
		}
		throw new TradeSettlementReportException("invalid input. allowed values (B | S)");

	}
}