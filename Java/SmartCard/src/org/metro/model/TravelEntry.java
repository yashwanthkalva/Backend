package org.metro.model;

public class TravelEntry {

	private int cardId;
	private int entryStationNumber;
	private int exitStationNumber;
	private double fare;
	private double balance;


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
	 * @return the fare
	 */
	public double getFare() {
		return fare;
	}

	/**
	 * @param fare
	 *            the fare to set
	 */
	public void setFare(double fare) {
		this.fare = fare;
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
}
