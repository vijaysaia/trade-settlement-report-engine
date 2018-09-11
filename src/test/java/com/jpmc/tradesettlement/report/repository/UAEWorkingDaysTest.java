package com.jpmc.tradesettlement.report.repository;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UAEWorkingDaysTest {

	private WorkingDays workingDays;

	@Before
	public void setUp() throws Exception {
		workingDays = UAEWorkingDays.getInstance();
	}

	@Test
	public void testFindFirstWorkingDate_Sunday() throws Exception {
		final LocalDate aSunday = LocalDate.of(2018, 9, 9);

		/* should return the same, since Sunday is a working day in UAE */
		assertEquals(aSunday, workingDays.findFirstWorkingDate(aSunday));
	}

	@Test
	public void testFindFirstWorkingDate_Friday() throws Exception {
		final LocalDate aFriday = LocalDate.of(2018, 9, 7);

		/*
		 * should return the first Sunday (9/9/2018), since Friday is not a working day
		 */
		assertEquals(LocalDate.of(2018, 9, 9), workingDays.findFirstWorkingDate(aFriday));
	}

	@Test
	public void testFindFirstWorkingDate_Saturday() throws Exception {
		final LocalDate aSaturday = LocalDate.of(2018, 9, 8);

		/*
		 * should return the first Sunday (9/9/2018), since Saturday is not a working
		 * day
		 */

		assertEquals(LocalDate.of(2018, 9, 9), workingDays.findFirstWorkingDate(aSaturday));
	}

}
