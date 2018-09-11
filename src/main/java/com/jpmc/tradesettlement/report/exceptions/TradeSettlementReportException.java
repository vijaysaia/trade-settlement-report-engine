package com.jpmc.tradesettlement.report.exceptions;

public class TradeSettlementReportException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public TradeSettlementReportException() {
		super();
	}

	public TradeSettlementReportException(String message) {
		super(message);
	}

	public TradeSettlementReportException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String getMessage() {
		return message;
	}

}
