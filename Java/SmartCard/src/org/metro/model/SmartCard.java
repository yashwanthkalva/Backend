package org.metro.model;

import java.util.ArrayList;
import java.util.List;

public class SmartCard {

	private int cardId;
	private double balance;
	private int entryStationNumber;
	private int exitStationNumber;
	private int footFall;
	List<TravelEntry> history = new ArrayList<TravelEntry>();

	/**
	 * @return the footFall
	 */
	public int getFootFall() {
		return footFall;
	}

	/**
	 * @param footFall
	 *            the footFall to set
	 */
	public void setFootFall(int footFall) {
		this.footFall = footFall;
	}

	/**
	 * @return the cardId
	 */
	public int getCardId() {

		return cardId;
	}

	/**
	 * @param cardId
	 *            the cardId to set
	 */
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the entryStationNumber
	 */
	public int getEntryStationNumber() {
		return entryStationNumber;
	}

	/**
	 * @param entryStationNumber
	 *            the entryStationNumber to set
	 */
	public void setEntryStationNumber(int entryStationNumber) {
		this.entryStationNumber = entryStationNumber;
	}

	/**
	 * @return the exitStationNumber
	 */
	public int getExitStationNumber() {
		return exitStationNumber;
	}

	/**
	 * @param exitStationNumber
	 *            the exitStationNumber to set
	 */
	public void setExitStationNumber(int exitStationNumber) {
		this.exitStationNumber = exitStationNumber;
	}

	/**
	 * @return the history
	 */
	public List<TravelEntry> getHistory() {
		return history;
	}

	/**
	 * @param history
	 *            the history to set
	 */
	public void setHistory(List<TravelEntry> history) {
		this.history = history;
	}

}
