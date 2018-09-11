package com.jpmc.tradesettlement.report.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract  class WorkingDaysImpl  implements WorkingDays{
	
	
	protected Map<DayOfWeek, Boolean> isWorkingDayMap = new HashMap<DayOfWeek, Boolean>();

    protected abstract void setupWorkingDays();

    public WorkingDaysImpl() {
        setupWorkingDays();
    }

    public LocalDate findFirstWorkingDate(LocalDate date) {

        /* check if there is really an available weekday */
        if (isWorkingDayMap.values().stream().noneMatch(b -> b)) {
            return null;
        }

        /* if there are available working day, then check for the first working day */
        return findFirstWorkingDateRec(date);
    }

    private LocalDate findFirstWorkingDateRec(LocalDate date) {
        final DayOfWeek inputDay = date.getDayOfWeek();

        /* in case the given date is working date, just return it */
        if (isWorkingDayMap.get(inputDay)) {
            return date;
        } else {
            /* o.w look for the next working date (Recursively) */
            return findFirstWorkingDateRec(date.plusDays(1));
        }
    }

}
