package org.metro.api;

import org.metro.model.SmartCard;

public class ReportManager implements IReportManager {

	@Override
	public void getCardStatusReport(SmartCard card) {

		System.out.println("The Card Number : " + card.getCardId()
				+ " was used to travel from station A"
				+ card.getEntryStationNumber() + " to station A"
				+ card.getExitStationNumber()
				+ " and the current balance on the card is :"
				+ card.getBalance());

	}

}
