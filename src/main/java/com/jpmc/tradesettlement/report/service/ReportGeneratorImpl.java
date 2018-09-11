package com.jpmc.tradesettlement.report.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.jpmc.tradesettlement.report.beans.Rank;
import com.jpmc.tradesettlement.report.beans.Ticker;

public class ReportGeneratorImpl implements ReportGenerator {

	private StringBuilder stringBuilder = new StringBuilder();

	@Override
	public String generateTickersReport(Set<Ticker> instructions) {
		/* calc the correct settlement dates */
		TickerSettlementDateCalc.calcSettlementDates(instructions);

		/* Building the report string */
		return generateDailyOutgoingRanking(instructions, generateDailyIncomingRanking(instructions,
				generateDailyIncomingAmount(instructions, generateDailyOutgoingAmount(instructions, stringBuilder))))
						.toString();
	}

	private static StringBuilder generateDailyOutgoingAmount(Set<Ticker> instructions, StringBuilder stringBuilder) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = TickerSettlementStatsCalc
				.calcDailyOutgoingAmount(instructions);

		stringBuilder.append("\n*****************************************\n")
				.append("         Outgoing Daily Amount          \n")
				.append("*****************************************\n")
				.append("      Date       |    Trade Amount      \n")
				.append("*****************************************\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			stringBuilder.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingAmount(Set<Ticker> instructions, StringBuilder stringBuilder) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = TickerSettlementStatsCalc
				.calcDailyIncomingAmount(instructions);

		stringBuilder.append("\n*****************************************\n")
				.append("         Incoming Daily Amount          \n")
				.append("*****************************************\n")
				.append("      Date       |    Trade Amount      \n")
				.append("*****************************************\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			stringBuilder.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyOutgoingRanking(Set<Ticker> instructions, StringBuilder stringBuilder) {
		final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = TickerSettlementStatsCalc
				.calcDailyOutgoingRanking(instructions);

		stringBuilder.append("\n*****************************************\n")
				.append("         Outgoing Daily Ranking          \n")
				.append("*****************************************\n")
				.append("     Date    |     Rank   |   Entity     \n")
				.append("*****************************************\n");

		for (LocalDate date : dailyOutgoingRanking.keySet()) {
			for (Rank rank : dailyOutgoingRanking.get(date)) {
				stringBuilder.append(date).append("   |      ")
							.append(rank.getRank()).append("     |    ")
							.append(rank.getEntity()).append("\n");
			}
		}

		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingRanking(Set<Ticker> instructions, StringBuilder stringBuilder) {
		final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = TickerSettlementStatsCalc
				.calcDailyIncomingRanking(instructions);

		stringBuilder.append("\n*****************************************\n")
				.append("         Incoming Daily Ranking          \n")
				.append("*****************************************\n")
				.append("     Date    |     Rank   |   Entity     \n")
				.append("*****************************************\n");

		for (LocalDate date : dailyIncomingRanking.keySet()) {
			for (Rank rank : dailyIncomingRanking.get(date)) {
				stringBuilder.append(date).append("   |      ")
				.append(rank.getRank()).append("     |    ")
				.append(rank.getEntity()).append("\n");
			}
		}

		return stringBuilder;
	}

}
