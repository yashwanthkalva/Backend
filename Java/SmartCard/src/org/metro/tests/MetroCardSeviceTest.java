package org.metro.tests;

import java.util.Scanner;

import org.metro.api.BalanceManager;
import org.metro.api.IBalanceManager;
import org.metro.api.ISwipeManager;
import org.metro.api.SwipeManager;
import org.metro.model.SmartCard;

public class MetroCardSeviceTest {

	public static void main(String args[]) {
		// Enter the cardID and stationNumber with one
		// Test case
		System.out.println("Please provide card Id :");
		Scanner input = new Scanner(System.in);
		SmartCard card = new SmartCard();
		int cardId = input.nextInt();
		card.setCardId(cardId);

		System.out.println("Account Balance :" + card.getBalance());

		IBalanceManager balanceManager = new BalanceManager();
		// recharge with 100 by default
		balanceManager.rechargeCard(card, 100);
		
		while (card.getBalance() >= IBalanceManager.minimumBalance) {
			System.out.println("Account Balance :" + card.getBalance());
			System.out
					.println("Please provide entry station number to SwipeIn :");
			input = new Scanner(System.in);
			int stationNumber = input.nextInt();
			ISwipeManager swipeManager = new SwipeManager(stationNumber);
			boolean entryAllowed = swipeManager.swipeIn(card);
			
			
			if (entryAllowed) {
				System.out.println("Your Account Balance : "
						+ card.getBalance());
				System.out.println("Open Barricades, Good Day :) !!!!");
			} else {
				System.out
						.println("X Insufficient balance, entry restricted X");
				// Exit with Zero as it is normal termination
				System.exit(0);
			}
			/*
			 * Serialize the card object state to to file system or database as
			 * it has balance,entry station number stored
			 */
			// Exit station is code here to test with entry station as 2 and
			// exit
			// station as 5
			System.out
					.println("Please provide exit station number to SwipeOut :");
			input = new Scanner(System.in);
			
			//You can use same swipe manager, need to change arguements of swipein and swipe out methods to pass station number
			swipeManager = new SwipeManager(input.nextInt());
			boolean exitAllowed = swipeManager.swipeOut(card);
			
			if (exitAllowed) {
				System.out
						.println("Your Account Balance :" + card.getBalance());
				System.out.println("Open Barricades, Bye :) !!!!");
			} else {
				System.out
						.println("X Insufficient balance, exit restricted, please recharge now X");
				// Exit with Zero as it is normal termination
				System.exit(0);
			}
		}
	}
}
