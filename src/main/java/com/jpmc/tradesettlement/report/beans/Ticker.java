package com.jpmc.tradesettlement.report.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class Ticker {

	private final String entity;
	private final TradeAction action;
	private final LocalDate instructionDate;
	private LocalDate settlementDate;
	private final TickerDetails details;

	public Ticker(String entity, TradeAction action, LocalDate instructionDate, LocalDate settlementDate,
			TickerDetails details) {
		this.entity = entity;
		this.action = action;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.details = details;
	}
	
	
	 public String getEntity() {
	        return entity;
	    }

	    public TradeAction getAction() {
	        return action;
	    }

	    public LocalDate getInstructionDate() {
	        return instructionDate;
	    }

	    public void setSettlementDate(LocalDate newDate) {
	        settlementDate = newDate;
	    }

	    public LocalDate getSettlementDate() {
	        return settlementDate;
	    }

	    public TickerDetails getDetails() {
	        return details;
	    }

	    public Currency getCurrency() {
	        return getDetails().getCurrency();
	    }

	    public BigDecimal getAgreedFx() {
	        return getDetails().getAgreedFx();
	    }

	    public int getUnits() {
	        return getDetails().getUnits();
	    }

	    public BigDecimal getPricePerUnit() {
	        return getDetails().getPricePerUnit();
	    }

	    public BigDecimal getTradeAmount() {
	        return getDetails().getTradeAmount()
	                .setScale(2, BigDecimal.ROUND_HALF_EVEN);
	    }

	    @Override
	    public String toString() {
	        return entity;
	    }
}
