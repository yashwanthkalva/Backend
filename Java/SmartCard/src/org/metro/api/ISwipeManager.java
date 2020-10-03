package org.metro.api;

import org.metro.exceptions.MetroApplicationException;
import org.metro.model.SmartCard;

public interface ISwipeManager {
	boolean swipeIn(SmartCard card);
	boolean swipeOut(SmartCard card)  ;
}
