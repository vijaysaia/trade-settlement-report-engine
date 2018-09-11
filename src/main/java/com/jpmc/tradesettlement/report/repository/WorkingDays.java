package com.jpmc.tradesettlement.report.repository;

import java.time.LocalDate;

public interface WorkingDays {

	/**
	 * 
	 * @param date
	 * @return LocalDate
	 */
	 LocalDate findFirstWorkingDate(LocalDate date);
}
