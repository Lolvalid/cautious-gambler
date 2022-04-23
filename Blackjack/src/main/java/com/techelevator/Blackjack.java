package com.techelevator;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Blackjack {

	public static void main(String[] args) {
		final int BLACKJACK_BABY = 21;
		int playerTotal = 0;

		playerTotal += hitDeck();
		playerTotal += hitDeck();
		System.out.println("*******************");
		System.out.println("Your total is: " + playerTotal + ".");
		System.out.println("*******************");
		System.out.println("[H]it or [S]tay?");
		}

	public static int hitDeck() {
		final int BLACKJACK_BABY = 21;
		int playerTotal = 0;
		int[][] theDeck = cardInteractions.resetTheDeck();
		int cardValue[] = new int[1];
		boolean wasThereAnAce = false;
		int cardDrawn = 0;

		cardValue = cardInteractions.hitTheDeck(theDeck);


		//checking if ace will bust player, if so sets ace to be 1.
		if (cardValue[1] == 1) {
			if(cardDrawn + playerTotal > BLACKJACK_BABY){
				cardDrawn = 1;
			}

		}
		playerTotal = cardValue[0];
		return playerTotal;
	}
}

