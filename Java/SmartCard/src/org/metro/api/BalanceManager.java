package org.metro.api;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.metro.exceptions.MetroApplicationException;
import org.metro.model.SmartCard;

public class BalanceManager implements IBalanceManager {

	boolean isSwipeIn = false;
	Date today = new Date();

	@Override
	public boolean validateMinBalance(double balance) {
		if (balance < IBalanceManager.minimumBalance && isSwipeIn)
			return false;
		else
			return true;
	}

	@Override
	public SmartCard deductFare(SmartCard card) {
		double charges = calculateCharges(card);
		double balance = card.getBalance() - charges;
		card.setBalance(balance);
		return card;
	}

	@Override
	public void rechargeCard(SmartCard card, double rechargeAmount) {
		double balance = card.getBalance() + rechargeAmount;
		card.setBalance(balance);
	}

	@SuppressWarnings("unused")
	private double calculateCharges(SmartCard card) {

		if (Calendar.DAY_OF_WEEK == 7 || Calendar.DAY_OF_WEEK == 1) {
			return (Math.abs(card.getEntryStationNumber()
					- card.getExitStationNumber()))
					* IBalanceManager.weekEndChargePerStation;
		} else {
			return (Math.abs(card.getEntryStationNumber()
					- card.getExitStationNumber()))
					* IBalanceManager.weekDayChargePerStation;
		}

	}

	@Override
	public boolean validateBalanceWithCharges(SmartCard card) {
		if (card.getBalance() >= calculateCharges(card))
			return true;
		else
			return false;
	}

}
