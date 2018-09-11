package com.jpmc.tradesettlement.report.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import com.jpmc.tradesettlement.report.beans.Rank;
import com.jpmc.tradesettlement.report.beans.Ticker;
import com.jpmc.tradesettlement.report.beans.TradeAction;

public class TickerSettlementStatsCalc {
	/* Create a predicate for outgoing */
	private final static Predicate<Ticker> outgoingTickersPredicate = ticker -> ticker.getAction()
			.equals(TradeAction.BUY);

	/* Create a predicate for incoming */
	private final static Predicate<Ticker> incomingTickersPredicate = ticker -> ticker.getAction()
			.equals(TradeAction.SELL);

	/**
	 * calcs the daily outgoing (BUY) trade amount in USD
	 * 
	 * @param tickers
	 *            the ticker to calc the stats from
	 * @return a map from date to total amount
	 */
	public static Map<LocalDate, BigDecimal> calcDailyOutgoingAmount(Set<Ticker> tickers) {
		return calcDailyAmount(tickers, outgoingTickersPredicate);
	}

	/**
	 * calcs the daily incoming (SELL) trade amount in USD
	 * 
	 * @param tickers
	 *            the ticker to calc the stats from
	 * @return a map from date to total amount
	 */
	public static Map<LocalDate, BigDecimal> calcDailyIncomingAmount(Set<Ticker> tickers) {
		return calcDailyAmount(tickers, incomingTickersPredicate);
	}

	/**
	 * Ranks the daily outgoing (BUY) by trade amount in USD
	 * 
	 * @param tickers
	 *            the ticker to calc the stats from
	 * @return a map from date to a list of ranks (ranking)
	 */
	public static Map<LocalDate, LinkedList<Rank>> calcDailyOutgoingRanking(Set<Ticker> tickers) {
		return calcRanking(tickers, outgoingTickersPredicate);
	}

	/**
	 * Ranks the daily incoming (SELL) by trade amount in USD
	 * 
	 * @param tickers
	 *            the ticker to calc the stats from
	 * @return a map from date to a list of ranks (ranking)
	 */
	public static Map<LocalDate, LinkedList<Rank>> calcDailyIncomingRanking(Set<Ticker> tickers) {
		return calcRanking(tickers, incomingTickersPredicate);
	}

	private static Map<LocalDate, BigDecimal> calcDailyAmount(Set<Ticker> tickers, Predicate<Ticker> predicate) {
		return tickers.stream().filter(predicate).collect(groupingBy(Ticker::getSettlementDate,
				mapping(Ticker::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));
	}

	private static Map<LocalDate, LinkedList<Rank>> calcRanking(Set<Ticker> tickers, Predicate<Ticker> predicate) {
		final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();

		tickers.stream().filter(predicate).collect(groupingBy(Ticker::getSettlementDate, toSet()))
				.forEach((date, dailytickerSet) -> {
					final AtomicInteger rank = new AtomicInteger(1);

					final LinkedList<Rank> ranks = dailytickerSet.stream()
							.sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
							.map(ticker -> new Rank(rank.getAndIncrement(), ticker.getEntity(), date))
							.collect(toCollection(LinkedList::new));

					ranking.put(date, ranks);
				});

		return ranking;
	}

}
