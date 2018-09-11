package com.jpmc.tradesettlement.report.repository;

import java.time.DayOfWeek;

public class UAEWorkingDays extends WorkingDaysImpl {

	private static UAEWorkingDays instance = null;

	private UAEWorkingDays() {
		super();
	}

	public static UAEWorkingDays getInstance() {
		if (instance == null) {
			instance = new UAEWorkingDays();
		}
		return instance;
	}

	@Override
	protected void setupWorkingDays() {
		/* working days */
		this.isWorkingDayMap.put(DayOfWeek.SUNDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
		this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
		/* non working days */
		this.isWorkingDayMap.put(DayOfWeek.FRIDAY, false);
		this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false);
	}

}
