package org.metro.api;

import org.metro.exceptions.MetroApplicationException;
import org.metro.model.SmartCard;

public interface IBalanceManager {
	double minimumBalance=5.5;
	double weekDayChargePerStation=7;
	double weekEndChargePerStation=5.5;
	boolean validateMinBalance(double balance) ;
	SmartCard deductFare(SmartCard card) ;
	void rechargeCard(SmartCard card,double rechargeAmount);
	boolean validateBalanceWithCharges(SmartCard card);
}
