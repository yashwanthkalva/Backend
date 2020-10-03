package org.metro.tests;

import java.util.Scanner;

import org.metro.api.BalanceManager;
import org.metro.api.IBalanceManager;
import org.metro.api.IReportManager;
import org.metro.api.ISwipeManager;
import org.metro.api.ReportManager;
import org.metro.api.SwipeManager;
import org.metro.model.SmartCard;

public class PerCardReportTest {

	public static void main(String args[]) {
		SmartCard card = new SmartCard();
		card.setCardId(765432);
		IBalanceManager balanceManager = new BalanceManager();
		// recharge with 100 by default
		balanceManager.rechargeCard(card, 100);
		ISwipeManager swipeManager = new SwipeManager(1);
		//Swipe In
		swipeManager.swipeIn(card);
		swipeManager = new SwipeManager(5);
		//Swipe In
		swipeManager.swipeOut(card);
		IReportManager reportManager= new ReportManager();
		reportManager.getCardStatusReport(card);
		
		//For detailed report history can be maintained as list of travel entries as composition to SmartCard Obj
	}
}
