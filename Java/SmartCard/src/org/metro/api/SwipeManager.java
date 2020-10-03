package org.metro.api;

import org.metro.common.CommonUtility;
import org.metro.exceptions.MetroApplicationException;
import org.metro.model.SmartCard;
import org.metro.model.TravelEntry;

public class SwipeManager implements ISwipeManager {
	IBalanceManager balanceManager = new BalanceManager();

	int stationNumber;

	public SwipeManager(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	@Override
	public boolean swipeIn(SmartCard card) {
		if (balanceManager.validateMinBalance(card.getBalance())) {
			card.setEntryStationNumber(stationNumber);
			int oldValue=card.getFootFall();
			int newValue= CommonUtility.incrementFootFall(oldValue);
			card.setFootFall(newValue);
			return true;
		}
		return false;
	}

	@Override
	public boolean swipeOut(SmartCard card)  {
		card.setExitStationNumber(stationNumber);
		if (balanceManager.validateBalanceWithCharges(card)) {
			card.setBalance(balanceManager.deductFare(card).getBalance());
			int oldValue=card.getFootFall();
			int newValue= CommonUtility.incrementFootFall(oldValue);
			card.setFootFall(newValue);
				return true;
		}
		return false;
	}

}
