package com.jpmc.tradesettlement.report.repository;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.tradesettlement.report.repository.DefaultWorkingDays;
import com.jpmc.tradesettlement.report.repository.WorkingDays;

import static org.junit.Assert.assertEquals;

public class DefaultWorkingDaysTest {

	private WorkingDays workingDays;

	@Before
	public void setUp() throws Exception {
		workingDays = DefaultWorkingDays.getInstance();
	}

	@Test
	public void testFindFirstWorkingDate_Monday() throws Exception {
		final LocalDate aMonday = LocalDate.of(2018, 9, 10);

		/*should return the same, since Monday is a working by default */
		assertEquals(aMonday, workingDays.findFirstWorkingDate(aMonday));
	}

	@Test
	public void testFindFirstWorkingDate_Friday() throws Exception {
		final LocalDate aFriday = LocalDate.of(2018, 9, 7);

		/* should return the same, since Friday is a working by default */
		assertEquals(aFriday, workingDays.findFirstWorkingDate(aFriday));
	}

	@Test
	public void testFindFirstWorkingDate_Saturday() throws Exception {
		final LocalDate aSaturday = LocalDate.of(2018, 9, 8);

		/* should return the first Monday (10/9/2018), since Saturday is not a working day */
		assertEquals(LocalDate.of(2018, 9, 10), workingDays.findFirstWorkingDate(aSaturday));
	}

	@Test
	public void testFindFirstWorkingDate_Sunday() throws Exception {
		final LocalDate aSunday = LocalDate.of(2018, 9, 9);

		/* should return the first Monday (10/9/2018), since Sunday is not a working day */
		assertEquals(LocalDate.of(2018, 9, 10), workingDays.findFirstWorkingDate(aSunday));
	}

}
