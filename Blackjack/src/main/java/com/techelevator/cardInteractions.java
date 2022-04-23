package com.techelevator;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class cardInteractions {

	public static void main(String[] args) {

		resetTheDeck();
	}

	public static int[][] resetTheDeck() {
		//Initializing deck, 14 Ace - 1-13 numbers + 3 faces. Size 15 to account for 0 being a used card.
		int s = 0;
		int suiteSize = 4;
		int amountOfCards = 15;
		int[][] theDeck = new int[suiteSize][amountOfCards];
		boolean isSuiteFull = false;
		boolean hasSuiteLooped = false;
		//additional 2 loops to account for increase in suite, and reset card placement.
		for (int i = 0; i <= amountOfCards + 2; i++) {
			//Checking to see if we have filled all 4 suites.
			if (s == suiteSize) {
				isSuiteFull = true;
			}
			//checking to see if we have placed all the cards, and have we incremented suite.
			if (i > amountOfCards && !isSuiteFull && hasSuiteLooped) {
				i = 0;
				hasSuiteLooped = false;
				//check to see if we have placed all cards so we can increment suite.
			} else if (i > amountOfCards && !isSuiteFull) {
				s++;
				hasSuiteLooped = true;
				//Placing cards in the deck.
			} else if (i < amountOfCards) {
				theDeck[s][i] = i;
			}
		}
		return theDeck;

	}

	public static int[] hitTheDeck(int[][] theDeck) {
		Random drawCard = new Random();

		int amountOfCards = 14;
		int suiteSize = 3;
		int[][] deckHit = theDeck;
		int suiteChosen = drawCard.nextInt(suiteSize);
		int cardChosen = drawCard.nextInt(amountOfCards);
		String suiteName = "";
		String cardName = "";

		//redrawing card if value = 0 as has been used or is on table.
		if (cardChosen == 0){
			cardChosen = drawCard.nextInt(amountOfCards);
			suiteChosen = drawCard.nextInt(suiteSize);
		}

		//Removing card from the deck
		deckHit[suiteChosen][cardChosen] = 0;

		if (0 == cardChosen || cardChosen > 10) {
			switch (cardChosen) {
				case 11:
					cardName = "Jack";
					break;
				case 12:
					cardName = "Queen";
					break;
				case 13:
					cardName = "King";
					break;
				case 14:
					cardName = "Ace";
					break;
			}
		} else {
			cardName = String.valueOf(cardChosen);}

		switch (suiteChosen) {
			case 0:
				suiteName = "Diamonds.";
				break;
			case 1:
				suiteName = "Clubs.";
				break;
			case 2:
				suiteName = "Spades.";
				break;
			case 3:
				suiteName = "Hearts.";
				break;

		}
		try{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("You drew a " + cardName + " of " + suiteName);

		//giving values to face cards, informing main there was an ace in case of bust.

		int cardValue = cardChosen;
		int wasAnAce = 0;

		if (cardChosen == 14){
			cardValue = 11;
			wasAnAce = 1;
		} else if (cardChosen > 10){
			cardValue = 10;
		 }
		//defining array to store and return values
		int[] aceAndValue = {cardValue,wasAnAce};

		return aceAndValue;
	}
}


