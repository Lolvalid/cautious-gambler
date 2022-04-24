package com.techelevator;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class cardInteractions {
	static int[][] deckHit;
	public static int handSizeDealer=0;
	public static int handSizePlayer=0;

	public static void main(String[] args) {
	}

	public static int[][] buildTheDeck() {
		//Initializing deck, 14 Ace - 1-13 numbers + 3 faces. Size 15 to account for 0 being a used card.
		int s = 0;
		int suiteSize = 5;
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

	public static int[] hitTheDeck(int[][] theDeck, char whosCard) {
		Random drawCard = new Random();
		int spentCardOrSuite = 0;
		char player = 'P';
		char dealer = 'D';
		int amountOfCards = 15;
		int suiteSize = 5;
		deckHit = theDeck;
		int suiteChosen = drawCard.nextInt(suiteSize);
		int cardChosen = drawCard.nextInt(amountOfCards);
		String suiteName = "";
		String cardName = "";
		int minHandSizeToPrint = 1;

		//redrawing card if value = 0 as has been used or is on table.
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException ex) {
			ex.printStackTrace();}


		while (cardChosen == spentCardOrSuite || suiteChosen == spentCardOrSuite){
			cardChosen = drawCard.nextInt(amountOfCards);
			suiteChosen = drawCard.nextInt(suiteSize);
		}

		//Removing card from the deck
		deckHit[suiteChosen][cardChosen] =spentCardOrSuite;

		if (cardChosen > 10) {
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
			case 1:
				suiteName = "Diamonds.";
				break;
			case 2:
				suiteName = "Clubs.";
				break;
			case 3:
				suiteName = "Spades.";
				break;
			case 4:
				suiteName = "Hearts.";
				break;

		}
		//giving delaying hit a moment for suspense.
		try{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		if (whosCard == player) {
			System.out.println("You drew a " + cardName + " of " + suiteName);
			handSizePlayer++;
			playerChoices.playerHand(cardName, handSizePlayer);
		}
		if (whosCard == dealer) {
			System.out.println("The dealer drew a " + cardName + " of " + suiteName);
			handSizeDealer++;
			playerChoices.dealerHand(cardName,handSizeDealer);
		}


		if (handSizePlayer > minHandSizeToPrint && whosCard == player) {
			playerChoices.showHand(player);
		} else if(handSizeDealer > minHandSizeToPrint && whosCard == dealer) {
			playerChoices.showHand(dealer);
		}

		//giving values to face cards, informing main there was an ace in case of bust.

		int cardValue = cardChosen;
		int minFaceCardValue = 10;
		int wasAnAce = 0;
		int valueForAceDrawn = 14;

		if (cardChosen == valueForAceDrawn){
			cardValue = 11;
			wasAnAce = 1;
		} else if (cardChosen > minFaceCardValue){
			cardValue = 10;
		 }
		//defining array to store and return values
		int[] aceAndValue = {cardValue,wasAnAce};

		return aceAndValue;

	}
	//Setting cards 1-10 as string values.
	public String convertCardNumberToString(int cardDrawn){
		String convertedNumber = Integer.toString(cardDrawn);
		return convertedNumber;
	}
}


